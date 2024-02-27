plugins {
    kotlin("jvm") version "1.9.22"
}

group = "org.not.given"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-cli-jvm
    implementation("org.jetbrains.kotlinx:kotlinx-cli:0.3.6")
    // https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-coroutines-core
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
    implementation("org.camunda.bpm.model:camunda-bpmn-model:7.20.0")

    testImplementation("org.jetbrains.kotlin:kotlin-test")
    // https://mvnrepository.com/artifact/org.camunda.bpm.model/camunda-bpmn-model
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(20)
}