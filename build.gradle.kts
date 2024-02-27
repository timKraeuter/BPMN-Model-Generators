plugins {
    kotlin("jvm") version "1.9.22"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-cli-jvm
    implementation("org.jetbrains.kotlinx:kotlinx-cli:0.3.6")
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