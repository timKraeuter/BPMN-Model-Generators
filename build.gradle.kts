plugins {
    kotlin("jvm") version "1.9.22"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "org.not.given"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.github.ajalt.clikt:clikt:4.2.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
    implementation("org.camunda.bpm.model:camunda-bpmn-model:7.20.0")

    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(20)
}

tasks.shadowJar {
    manifest {
        // Choose a main class here to build the specific CLI tool.
//        attributes["Main-Class"] = "parallel.ParallelBranchMainKt"
//        attributes["Main-Class"] = "blocks.BlockMainKt"
        attributes["Main-Class"] = "statistics.StatsMainKt"
    }
}