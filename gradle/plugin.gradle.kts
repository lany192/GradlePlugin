//plugins {
//    alias(libs.plugins.upload.plugin)
//}
//
//upload {
//    task_depends = "build"
//    server_url = "http://127.0.0.1:8000/upload"
//    file_path = "$rootDir/app/build/outputs/apk/release/app-release-unsigned.apk"
//    parameters = mapOf(
//        "param1" to "John Doe",
//        "param2" to "John Doe2",
//        "param3" to "New York"
//    )
//}