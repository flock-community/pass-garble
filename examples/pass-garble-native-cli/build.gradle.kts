// build.gradle.kts
plugins {
    kotlin("multiplatform") version "1.6.21"
}


version = "0.0.2"

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
                    implementation("community.flock.pass-garble:pass-garble-native:0.0.2")
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
