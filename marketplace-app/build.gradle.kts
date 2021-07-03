import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    groovy
    kotlin("jvm")
    id("org.jetbrains.kotlin.plugin.spring") version "1.5.20"
    id("org.springframework.boot") version "2.5.2"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":marketplace-utils"))

    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.12.3")
    implementation("javax.validation:validation-api:2.0.1.Final")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.spockframework:spock-core:2.0-groovy-3.0")
}

application {
    mainClass.set("pl.allegro.training.kotlin.marketplace.MarketplaceApplicationKt")
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
