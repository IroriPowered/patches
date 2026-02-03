plugins {
    id("java")
}

group = "cc.irori"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.hytale.com/release")
    maven("https://cursemaven.com")
}

dependencies {
    compileOnly(libs.hytale)
    compileOnly(libs.hyxin)
}
