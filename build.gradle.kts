plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.testng:testng:7.7.0")
    implementation ("org.apache.logging.log4j:log4j-core:2.17.0")
    implementation ("org.apache.logging.log4j:log4j-api:2.17.0")
}

tasks.test {
    useJUnitPlatform()
}