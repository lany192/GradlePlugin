package com.github.lany192.upload

open class PluginExtension {
    var task_depends: String = "assemble"
    var server_url: String = ""
    var file_path: String = ""
    var parameters: Map<String, String> = mapOf()
}