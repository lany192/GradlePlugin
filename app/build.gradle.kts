plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.upload.plugin)
}

upload {
    task_name = "upload_file_to_server2"
    task_depends = "assemble"
    server_url = "http://127.0.0.1:8000/upload"
    file_paths = listOf(
        "$rootDir/app/build/outputs/apk/debug/app-debug.apk",
        "$rootDir/app/build/outputs/apk/release/app-release-unsigned.apk"
    )
    file_param_name = "files"
    parameters = mapOf(
        "param1" to "John Doe",
        "param2" to "John Doe2",
        "param3" to "New York"
    )
}

android {
    namespace = "com.github.lany192.plugin.upload"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.github.lany192.plugin.upload"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
//apply(from = "../gradle/plugin.gradle.kts")
