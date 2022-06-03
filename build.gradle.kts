import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig.Mode

plugins {
    kotlin("multiplatform") version "1.6.21"
    kotlin("native.cocoapods") version "1.6.21"
    id("dev.petuska.npm.publish") version "2.1.2"
    id("maven-publish")
    application
}

val passGarbleVersion = "0.0.2"
group = "community.flock.pass-garble"
version = passGarbleVersion

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

    js(IR) {
        compilations.all {
            compileKotlinTask.kotlinOptions.freeCompilerArgs += listOf("-opt-in=kotlin.RequiresOptIn")
        }
        binaries.library()
        browser {
            commonWebpackConfig {
                mode = if (project.hasProperty("prod")) Mode.PRODUCTION else Mode.DEVELOPMENT
            }
        }
    }
    val hostOs = System.getProperty("os.name")
    val isMingwX64 = hostOs.startsWith("Windows")
    val nativeTarget = when {
        hostOs == "Mac OS X" -> macosX64("native")
        hostOs == "Linux" -> linuxX64("native")
        isMingwX64 -> mingwX64("native")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }

    macosX64("native") {
        val main by compilations.getting {
            kotlinOptions {
                freeCompilerArgs = freeCompilerArgs + "-Xbundle-id=PassGarble"
            }
        }
        binaries {
            framework {
                baseName = "PassGarble"


            }
        }


    }


    cocoapods {
        // Required properties
        // Specify the required Pod version here. Otherwise, the Gradle project version is used.
        version = passGarbleVersion
        summary = "PassGarble - a kotlin multiplatform password generator"
        homepage = "https://github.com/flock-community/pass-garble"

        // Optional properties
        // Configure the Pod name here instead of changing the Gradle project name
        name = "PassGarble"



        framework {
            baseName = "PassGarble"
            osx.deploymentTarget = "11.0"
        }
        // Maps custom Xcode configuration to NativeBuildType
        xcodeConfigurationToNativeBuildType["CUSTOM_DEBUG"] =
            org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.DEBUG
        xcodeConfigurationToNativeBuildType["CUSTOM_RELEASE"] =
            org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.RELEASE

    }


    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")
            }

        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.1")
            }
        }
        val jvmMain by getting
        val jvmTest by getting
        val jsMain by getting {
            dependencies {
                implementation("com.ionspin.kotlin:multiplatform-crypto-libsodium-bindings:0.8.6")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:1.6.1")

            }
        }
        val jsTest by getting
        val nativeMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.3.2")

            }
        }
        val nativeTest by getting
    }


    val publicationsFromMainHost = listOf(jvm(), js()).map { it.name } + "kotlinMultiplatform"
    val jfrogHost = "https://flock.jfrog.io"
    publishing {
        repositories {
            mavenLocal()
            maven {
                name = "JFrog"
                // This requires you to have an environment variable NexusUsername and NexusPassword when this is executed
                // or: put the properties in your ~/.gradle/gradle.properties file (or equivalent)
                // See https://docs.gradle.org/current/userguide/declaring_repositories.html#sec:handling_credentials
                credentials(PasswordCredentials::class)

                val releasesRepoUrl = "$jfrogHost/artifactory/flock-maven/"
                val snapshotsRepoUrl = "$jfrogHost/artifactory/flock-maven/"
                url = uri(if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl)
            }
        }

        publications {
            matching { it.name in publicationsFromMainHost }.all {
                val targetPublication = this@all
                tasks.withType<AbstractPublishToMaven>()
                    .matching { it.publication == targetPublication }
            }
        }
    }
}

npmPublishing {
    dry = true
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
