package com.github.lany192.plugin.upload

import org.gradle.api.Project

class PluginExtension(project: Project) {
    var name: String = ""
    var chapter: String = ""
    var subExtension: SubExtension = SubExtension()

    init {
        subExtension = project.extensions.create("sub", SubExtension::class.java)
    }
}