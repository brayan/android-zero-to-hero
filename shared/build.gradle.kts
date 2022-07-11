plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
}

version = "1.0"

kotlin {
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
        }
    }
    
    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(Coroutines.core)
                implementation(MockK.core)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Coroutines.core)
                implementation(Coroutines.android)
                implementation(Lifecycle.viewModel)
                implementation(Lifecycle.runtime)
                implementation(Lifecycle.liveData)
                implementation(Koin.android)
                implementation(AndroidX.appcompat)
                implementation(AndroidX.recyclerview)
                implementation(AndroidX.constraintLayout)
                implementation(AndroidX.materialDesign)
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(Junit.junit)
                implementation(MockK.core)
                implementation(Kotlin.test)
                implementation(Coroutines.test)
                implementation(Lifecycle.test)
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    compileSdk = 32
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 21
        targetSdk = 32
    }
}