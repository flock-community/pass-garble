// build.gradle.kts
plugins {
    kotlin("multiplatform") version "1.7.10"
}


version = "0.0.3-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
}


kotlin {

//    sourceSets {
//        val nativeMain by getting {
//            dependencies {
//            }
//        }
//    }
    macosX64("native") {// on macOS
        compilations {
            val main by getting {
                dependencies {
                    implementation("community.flock.pass-garble:pass-garble-native:0.0.3-SNAPSHOT")
                    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")

                }
            }
        }

        binaries {
            executable()
        }
    }
}

tasks.withType<Wrapper> {
    gradleVersion = "6.7.4"
    distributionType = Wrapper.DistributionType.BIN
}
