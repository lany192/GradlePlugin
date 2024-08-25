package com.github.lany192.upload

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction


open class HelloTask : DefaultTask() {
    @get:OutputDirectory
    private val output = project.layout.buildDirectory.dir("output")

    @TaskAction
    fun execute() {
        logger.lifecycle("Hello from the plugin!$output")
    }
}