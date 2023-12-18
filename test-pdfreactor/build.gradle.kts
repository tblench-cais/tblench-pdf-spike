plugins {
    kotlin("jvm") version "1.9.21"
    application
}

group = "org.blench.test"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
    maven(url = "https://packages.confluent.io/maven/")
    maven {
        name = "CAIS GitHub Packages"
        url = uri("https://maven.pkg.github.com/cais-group/cais-packages")
        credentials {
            username = project.findProperty("github.user") as String? ?: System.getenv("GITHUB_USER")
            password = project.findProperty("github.token") as String? ?: System.getenv("GITHUB_TOKEN")
        }
    }
}


dependencies {
    testImplementation(kotlin("test"))
    implementation("com.realobjects:pdfreactor:11.6.5")
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