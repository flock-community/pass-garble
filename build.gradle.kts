import org.jetbrains.kotlin.gradle.plugin.mpp.BitcodeEmbeddingMode
import org.jetbrains.kotlin.gradle.plugin.mpp.BitcodeEmbeddingMode.BITCODE
import org.jetbrains.kotlin.gradle.plugin.mpp.Framework.BitcodeEmbeddingMode.BITCODE
import org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig.Mode

plugins {
    kotlin("multiplatform") version "1.7.10"
    kotlin("native.cocoapods") version "1.7.10"
    id("dev.petuska.npm.publish") version "2.1.2"
    id("maven-publish")
}

val passGarbleVersion = "0.0.3-SNAPSHOT"
group = "community.flock.pass-garble"
version = passGarbleVersion

repositories {
    mavenCentral()
}


kotlin {
    jvmToolchain {
        (this as JavaToolchainSpec).languageVersion.set(JavaLanguageVersion.of("17")) // "8"
    }

    targets.all {
        compilations.all {
            kotlinOptions {
                freeCompilerArgs = freeCompilerArgs + "-opt-in=kotlin.RequiresOptIn"
            }
        }
    }

    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "17"
        }

        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
//        withJava()
    }

    js(IR) {
        binaries.library()
        browser {
            commonWebpackConfig {
                mode = if (project.hasProperty("prod")) Mode.PRODUCTION else Mode.DEVELOPMENT
            }
        }
    }

    macosX64() {
        val main by compilations.getting {
            kotlinOptions {
                freeCompilerArgs = freeCompilerArgs + listOf("-Xbundle-id=PassGarble")
            }
        }
        binaries {
            framework("pass-garble-objective-c-x64") {
                baseName = "PassGarble"
                embedBitcode = BitcodeEmbeddingMode.BITCODE
            }
        }
    }

//    macosArm64() {
//        val main by compilations.getting {
//            kotlinOptions {
//                freeCompilerArgs = freeCompilerArgs + listOf("-Xbundle-id=PassGarble")
//            }
//        }
//        binaries {
//            framework("pass-garble-objective-c-arm64") {
//                baseName = "PassGarble"
//                embedBitcode = BitcodeEmbeddingMode.BITCODE
//            }
//        }
//    }


    cocoapods {
        // Required properties
        // Specify the required Pod version here. Otherwise, the Gradle project version is used.
        version = passGarbleVersion
        summary = "PassGarble - a kotlin multiplatform password generator"
        homepage = "https://github.com/flock-community/pass-garble"

        // Optional properties
        // Configure the Pod name here instead of changing the Gradle project name
//        name = "PassGarble"
        osx.deploymentTarget = "11.0"
        podfile = project.file("./examples/PassGarbleMacOs/Podfile")

        framework {
            // Required properties
            // Framework name configuration. Use this property instead of deprecated 'frameworkName'
            baseName = "PassGarbleMacOS"
            osx.deploymentTarget = "11.0"


            // Optional properties
            // Dynamic framework support
//            isStatic = false
            // Dependency export
//            export(project(":anotherKMMModule"))
//            transitiveExport = false // This is default.
            // Bitcode embedding
//            embedBitcode(BITCODE)
        }

        // Maps custom Xcode configuration to NativeBuildType
        xcodeConfigurationToNativeBuildType["CUSTOM_DEBUG"] = NativeBuildType.DEBUG
        xcodeConfigurationToNativeBuildType["CUSTOM_RELEASE"] = NativeBuildType.RELEASE
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
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))

            }
        }

        val macosX64Main by getting
//        val macosArm64Main by getting
//        val macosSimulatorArm64Main by getting

        val macosX64Test by getting
//        val macosArm64Test by getting
//        val macosSimulatorArm64Test by getting



        val nativeMain by creating {
            dependsOn(commonMain)
            macosX64Main.dependsOn(this)
//            macosArm64Main.dependsOn(this)
//            macosSimulatorArm64Main.dependsOn(this)
        }
        val nativeTest by creating {
            dependsOn(commonTest)
            macosX64Test.dependsOn(this)
//            macosArm64Test.dependsOn(this)
//            macosSimulatorArm64Test.dependsOn(this)
        }

    }


    val publicationsFromMainHost = listOf(jvm(), js()).map { it.name } + "kotlinMultiplatform"
    val jfrogHost = "https://flock.jfrog.io"
//    publishing {
//        repositories {
//            mavenLocal()
//            maven {
//                name = "JFrog"
//                // This requires you to have an environment variable NexusUsername and NexusPassword when this is executed
//                // or: put the properties in your ~/.gradle/gradle.properties file (or equivalent)
//                // See https://docs.gradle.org/current/userguide/declaring_repositories.html#sec:handling_credentials
//                credentials(PasswordCredentials::class)
//
//                val releasesRepoUrl = "$jfrogHost/artifactory/flock-maven/"
//                val snapshotsRepoUrl = "$jfrogHost/artifactory/flock-maven/"
//                url = uri(if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl)
//            }
//        }
//
//        publications {
//            matching { it.name in publicationsFromMainHost }.all {
//                val targetPublication = this@all
//                tasks.withType<AbstractPublishToMaven>()
//                    .matching { it.publication == targetPublication }
//            }
//        }
//    }
}

//npmPublishing {
//    dry = true
//    organization = "flock"
//    repositories {
//        repository("npmjs") {
//            registry = uri("https://registry.npmjs.org")
//
//        }
//    }
//}

tasks.wrapper {
    distributionType = Wrapper.DistributionType.ALL
}
