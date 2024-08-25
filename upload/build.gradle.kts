plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
    `maven-publish`
}
group = "com.github.lany192"
version = "1.0.0"

dependencies {
    implementation(libs.build.gradle)
}

gradlePlugin {
    plugins {
        create("upload") {
            id = "com.github.lany192.upload"
            implementationClass = "com.github.lany192.UploadPlugin"
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifactId = "upload-gradle-plugin"
            from(components["java"])
        }
    }
    repositories {
        maven {
            url = uri("$rootDir/local-repo")
        }
        mavenLocal()
    }
}
