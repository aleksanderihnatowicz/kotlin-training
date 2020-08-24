import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    groovy
    kotlin("jvm") version "1.4.0"
    id("org.jetbrains.kotlin.plugin.spring") version "1.4.0"
    id("org.springframework.boot") version "2.2.2.RELEASE"
    id("io.spring.dependency-management") version "1.0.6.RELEASE"
}

repositories {
    jcenter()
}

dependencies {
    implementation(project(":marketplace-utils"))

    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.3")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.spockframework:spock-core:1.3-groovy-2.5")
}

application {
    mainClassName = "pl.allegro.training.kotlin.marketplace.MarketplaceApplicationKt"
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
    freeCompilerArgs = listOf("-XXLanguage:+InlineClasses")
}

val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
