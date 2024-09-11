plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.courotines.core)
            implementation(libs.datetime)
            implementation(libs.sqldelight.driver.common)
            implementation(libs.sqldelight.coroutines.extensions)
            implementation(libs.serialization.kotlin.core)

            implementation(libs.koin.core)
            implementation(libs.bundles.ktor.common)
            implementation(libs.korio)
            implementation(libs.androidx.datastore)
        }
        commonTest.dependencies {
            implementation(libs.bundles.test)
        }
        androidMain.dependencies {
            api(libs.lifecycle.viewmodel)
            implementation(libs.sqldelight.driver.android)
            implementation(libs.ktor.client.okhttp)
        }
        iosMain.dependencies {
            implementation(libs.sqldelight.driver.ios)
            implementation(libs.ktor.client.ios)
        }
    }
}


android {
    namespace = "com.ruzy.animeshub"
    compileSdk = 34
    defaultConfig {
        minSdk = 26
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

sqldelight{
    databases.create("AnimesHubDatabase"){
        packageName.set("com.ruzy.animeshub.database")
    }
}
