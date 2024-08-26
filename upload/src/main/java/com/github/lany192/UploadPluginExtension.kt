package com.github.lany192

open class UploadPluginExtension {
    var task_name: String = "upload_file_to_server"
    var task_depends: String = "assemble"
    var server_url: String = ""
    var file_paths: List<String> = listOf()
    var file_param_name: String = "files"
    var parameters: Map<String, String> = mapOf()
}