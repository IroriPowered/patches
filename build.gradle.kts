plugins {
    id("java")
}

group = "cc.irori"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://cursemaven.com")
}

dependencies {
    compileOnly(files("libs/HytaleServer.jar"))
    compileOnly("curse.maven:hyxin-1405491:7399430")
}
