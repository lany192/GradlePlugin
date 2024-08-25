package com.github.lany192

import org.gradle.api.Plugin
import org.gradle.api.Project

class UploadPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        println("这是插件：$this::class.java.name")
        val extension = project.extensions.create("upload", PluginExtension::class.java)

        project.tasks.create("UploadPluginTask")
            .doLast {
                println("这是插件 $this::class.java.name，它创建了一个 Task：${extension.name}")
                println("chapter = ${extension.chapter}")
//                println("author = ${extension.subExtension.author}")
            }
    }
}