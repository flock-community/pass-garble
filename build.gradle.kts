import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig.Mode

plugins {
    kotlin("multiplatform") version "1.6.10"
    id("dev.petuska.npm.publish") version "2.1.2"
    application
}

group = "community.flock.pass-garble"
version = "0.0.2-SNAPSHOT"

repositories {
    mavenCentral()
}


kotlin {

    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
        withJava()
    }

    js(IR){
        compilations.all {
            compileKotlinTask.kotlinOptions.freeCompilerArgs += listOf("-opt-in=kotlin.RequiresOptIn")
        }
        binaries.library()
        browser{
            commonWebpackConfig {
                mode = if(project.hasProperty("prod")) Mode.PRODUCTION else Mode.DEVELOPMENT
            }
        }
    }
    val hostOs = System.getProperty("os.name")
    val isMingwX64 = hostOs.startsWith("Windows")
//    val nativeTarget = when {
//        hostOs == "Mac OS X" -> macosX64("native")
//        hostOs == "Linux" -> linuxX64("native")
//        isMingwX64 -> mingwX64("native")
//        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
//    }

//    nativeTarget.apply {
//        binaries {
//            executable {
//                entryPoint = "main"
//            }
//        }
//    }
    sourceSets {
        val commonMain by getting {

        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val jvmMain by getting {

        }
        val jvmTest by getting
        val jsMain by getting {
            dependencies {
            }
        }
        val jsTest by getting
//        val nativeMain by getting
//        val nativeTest by getting
    }




}

npmPublishing {
    dry  = true
    organization = "flock"
    repositories {
        repository("npmjs") {
            registry = uri("https://registry.npmjs.org")

        }
    }
}

application {
    mainClass.set("MainKt")
}
