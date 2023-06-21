@file:Suppress("UNUSED_VARIABLE")


plugins {
    id("com.android.library")
    kotlin("multiplatform")
    kotlin("native.cocoapods")

    kotlin("plugin.serialization")
//    id("org.jlleitschuh.gradle.ktlint")
    id("org.jetbrains.compose")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()
    jvmToolchain(17)
    android {
        publishAllLibraryVariants()
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")

        framework {
            baseName = "shared"
            isStatic = true
            linkerOpts("-framework", "CoreLocation")
        }
        pod("MapboxMaps") { version = "~> 10.14.0" }
//        pod("MapboxCoreNavigation") { version = "~> 2.13.0" }
//        pod("MapboxNavigation") { version = "~> 2.13.0" }
    }
    
    sourceSets {
        all {
            languageSettings {
                optIn("kotlinx.coroutines.ExperimentalCoroutinesApi")
            }
        }

        val coroutinesVersion = "1.7.1"

        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")

            }
        }

        val androidMain by getting {
            dependencies {
                implementation("com.mapbox.maps:android:10.14.0")
            }
        }
    }
}

android {
    namespace = "com.my.test_kmm"
    compileSdk = 33
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

    defaultConfig {
        minSdk = 26
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
tasks.register<Copy>("copyAssetsToPods") {
    group = "Custom"
    description = "Copies asset files to the Pod's directory."

    // Specify the contents you want to copy
    from("iosApp/iosApp/Assets.xcassets/AccentColor.colorset") {
        include("**/*")
    }
    from("iosApp/iosApp/Assets.xcassets/AppIcon.appiconset") {
        include("**/*")
    }

    // Specify the target directory where you want to copy the files
    val targetDir = "$buildDir/cocoapods/synthetic/IOS/Pods/MapboxMaps/Sources/MapboxMaps/Location/Puck/IndicatorAssets.xcassets"
    into(targetDir)

    // Ensure directories exist before copy
    doFirst {
        File(targetDir).mkdirs()
    }
}

// Ensure your copy task runs at the right time, for example before a specific build task
tasks.named("build").configure {
    dependsOn("copyAssetsToPods")
}
