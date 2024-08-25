package com.github.lany192.plugin.upload

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction


open class HelloTask : DefaultTask() {
    private val output = project.layout.buildDirectory.dir("output")

    @TaskAction
    fun execute() {
        logger.lifecycle("Hello from the plugin!" + output)
    }
}