import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.21"
}

group = "io.bmotion"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {}

tasks.withType<KotlinCompile>() {
    // will add tornadofx which needs java 8
    kotlinOptions.jvmTarget = "1.8"
}