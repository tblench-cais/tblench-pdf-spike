plugins {
    kotlin("jvm") version "1.9.21"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        url = uri("https://repo.itextsupport.com/releases")
    }
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.itextpdf", "itext-core", "8.0.2")
    implementation("com.itextpdf", "html2pdf", "5.0.2")
    implementation("com.itextpdf.licensing", "licensing-base", "4.1.2")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}