/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Kotlin application project to get you started.
 * For more details take a look at the 'Building Java & JVM projects' chapter in the Gradle
 * User Manual available at https://docs.gradle.org/7.4/userguide/building_java_projects.html
 */
val picocli_version: String by project


val passGarbleCliVersion = "0.0.2"
group = "community.flock.pass-garble.cli"
version = passGarbleCliVersion

plugins {
    // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm") version "1.6.21"
    java
    // Apply the application plugin to add support for building a CLI application in Java.
    application
    kotlin("kapt") version "1.6.21"
    id("com.palantir.graal") version "0.10.0"

}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("community.flock.pass-garble:pass-garble-jvm:0.0.2")

    implementation("info.picocli:picocli:$picocli_version")
    kapt("info.picocli:picocli-codegen:$picocli_version")

    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

val main_class = "community.flock.passgarble.cli.PassGarbleCliKt"

application {
    // Define the main class for the application.
    mainClass.set(main_class)
}


tasks.withType<Jar> {

    manifest {
        attributes(
            mapOf(
                "Implementation-Title" to project.name,
                "Implementation-Version" to project.version,
                "Main-Class" to main_class
            )
        )
    }
}

kapt {
    arguments {
        arg("project", "${project.group}/${project.name}")
        arg("verbose")
    }
}

graal{
    outputName( "pass-garble-cli")
    javaVersion("11")
    graalVersion("22.1.0")
    mainClass(main_class)
    option("-H:ReflectionConfigurationFiles=additionalreflectionconfig.json")


}
