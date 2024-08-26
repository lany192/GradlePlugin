package com.github.lany192

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.asRequestBody
import okio.IOException
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.logging.Logger
import java.io.File

class UploadPlugin : Plugin<Project> {
    private lateinit var log: Logger
    override fun apply(project: Project) {
        log = project.logger
        log.lifecycle("开始构建上传插件")
        val extension = project.extensions.create("upload", UploadPluginExtension::class.java)

        project.afterEvaluate {
            log.lifecycle("任务名称:${extension.task_name}")
            log.lifecycle("服务器地址：${extension.server_url}")
            extension.file_paths.forEach {
                log.lifecycle("待上传文件路径： ${it}")
            }
            extension.parameters.let { config ->
                println("Processing configuration:")
                for ((key, value) in config) {
                    log.lifecycle("接收的参数: $key: $value")
                }
            }
            if (extension.file_paths.isEmpty()) {
                throw IllegalArgumentException("至少需要配置一个文件路径")
            }
            if (extension.server_url.isEmpty()) {
                throw IllegalArgumentException("服务器上传接口url地址不能为空")
            }
            if (extension.task_depends.isNotEmpty()) {
                project.tasks.create(extension.task_name) {
                    group = "upload"
                    dependsOn(extension.task_depends)
                    doLast {
                        uploadFile(
                            extension.server_url,
                            extension.file_paths,
                            extension.parameters,
                            extension.file_param_name
                        )
                    }
                }
            } else {
                project.tasks.create(extension.task_name) {
                    group = "upload"
                    doLast {
                        uploadFile(
                            extension.server_url,
                            extension.file_paths,
                            extension.parameters,
                            extension.file_param_name
                        )
                    }
                }
            }
        }
    }

    private fun uploadFile(
        serverUrl: String,
        paths: List<String>,
        parameters: Map<String, String>,
        fileKey: String = "file"
    ) {
        paths.forEach {
            val file = File(it)
            if (!file.exists()) {
                throw IllegalArgumentException("文件不存在，请检查： $it")
                return@uploadFile
            }
        }
        log.lifecycle("开始上传文件")
        try {
            val multipartBody = MultipartBody.Builder().setType(MultipartBody.FORM)
            parameters.map { (key, value) ->
                multipartBody.addFormDataPart(key, value)
            }
            paths.forEach {
                val file = File(it)
                multipartBody.addFormDataPart(
                    fileKey, file.name,
                    file.asRequestBody("application/octet-stream".toMediaTypeOrNull())
                )
            }
            val request = Request.Builder()
                .url(serverUrl)
                .post(multipartBody.build())
                .build()
            val client = OkHttpClient()
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) throw IOException("Unexpected code $response")
                // 打印服务器响应体
                log.lifecycle("上传结果： ${response.body?.string()}")
            }
        } catch (e: Exception) {
            log.lifecycle("上传失败： ${e.message}")
            e.printStackTrace()
        }
    }
}