package com.github.lany192.upload

import org.gradle.api.Plugin
import org.gradle.api.Project

class UploadPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        println("这是插件：$this::class.java.name")
        val extension = project.extensions.create("upload", PluginExtension::class.java)

        project.tasks.create("UploadPluginTask"){
            group = "upload"
            doLast {
                println("这是插件 $this::class.java.name，它创建了一个 Task：${extension.server_url}")
                println("chapter = ${extension.file_path}")
                println("接收的参数parameters: ${extension.parameters.joinToString()}")
            }
        }
    }
}