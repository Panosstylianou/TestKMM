buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle")

    }
}
plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.library") apply false
    kotlin("multiplatform") apply false
    id("com.android.application") apply false
    id("org.jetbrains.compose") apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.20" apply false
//    id("org.jlleitschuh.gradle.ktlint") version "11.3.1" apply false
}
tasks.register("printDaemonPath") {
    doLast {
        val os = java.io.ByteArrayOutputStream()
        exec {
            commandLine("sh", "-c", "echo \$PATH")
            standardOutput = os
        }
        println("Daemon PATH: ${os.toString().trim()}")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
