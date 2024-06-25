plugins {
    kotlin("jvm") version "1.8.0"
    id("org.jetbrains.dokka") version "1.9.20"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.8.10")

}

tasks.test {
    useJUnitPlatform()
}

tasks.register<Jar>("dokkaHtmlJar") {
    dependsOn(tasks.dokkaHtml)
    from(tasks.dokkaHtml.flatMap { it.outputDirectory })
    archiveClassifier.set("html-docs")
}

tasks.dokkaHtml{
    outputDirectory.set(layout.projectDirectory.dir("docs"))
}

kotlin {
    jvmToolchain(8)
}