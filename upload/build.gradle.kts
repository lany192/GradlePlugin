plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

dependencies {
    implementation(libs.build.gradle)
}

gradlePlugin {
    plugins {
        create("demo") {
            id = "com.example.printModuleName"
            implementationClass = "com.github.lany192.plugin.upload.UploadPlugin"
        }
    }
}