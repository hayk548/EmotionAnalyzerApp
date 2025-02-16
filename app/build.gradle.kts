plugins {
    alias(libs.plugins.android.application)
    id("com.google.gms.google-services") // Firebase and Google Services Plugin
}

android {
    namespace = "com.example.emotionanalyzerapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.emotionanalyzerapp"
        minSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildToolsVersion = "35.0.0"
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(platform("com.google.firebase:firebase-bom:33.9.0"))
    implementation("com.google.firebase:firebase-auth:")
    implementation("com.google.firebase:firebase-analytics:")
    implementation("com.google.firebase:firebase-firestore:")
//    implementation("com.google.cloud:google-cloud-speech:")
//    implementation("com.google.cloud:google-cloud-language:")

}