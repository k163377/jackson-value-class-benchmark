plugins {
    kotlin("jvm") version "2.0.21"
    id("me.champeau.jmh") version "0.7.3"
}

group = "org.wrongwrong"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven { setUrl("https://jitpack.io") }
}

val kogeraVersion = "2.19.0-beta25"

dependencies {
    jmhImplementation(kotlin("reflect"))

    jmhImplementation("com.github.ProjectMapK:jackson-module-kogera:$kogeraVersion")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}
