plugins {
    kotlin("jvm") version "1.9.21"
    application
}

group = "org.blench.test"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(kotlin("script-runtime"))
    implementation(group = "org.xhtmlrenderer", name = "flying-saucer-pdf-openpdf", version = "9.3.1")
    implementation(group = "org.apache.xmlgraphics", name = "xmlgraphics-commons", version = "2.9")
    implementation(group = "org.apache.xmlgraphics", name = "batik-bridge", version = "1.17")
    implementation(group = "org.apache.xmlgraphics", name = "batik-transcoder", version = "1.17")
    implementation(group = "org.apache.xmlgraphics", name = "batik-codec", version = "1.17")
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