plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.ruzy.animeshub.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.ruzy.animeshub.android"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
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
    implementation(projects.shared)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.bundles.material3)
    implementation(libs.androidx.navigation)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.metrics)
    implementation(libs.androidx.tracing)
    implementation(libs.coil.compose)
    implementation(libs.paging.compose)
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.lifecycle.runtime.compose)

    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
}