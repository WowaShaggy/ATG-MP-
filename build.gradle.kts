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
    testImplementation ("org.junit.jupiter:junit-jupiter:5.8.2")
    testImplementation("org.testng:testng:7.7.0")
    testImplementation ("org.seleniumhq.selenium:selenium-java:4.1.1")
    testImplementation ("io.github.bonigarcia:webdrivermanager:4.4.3")
    testImplementation ("io.rest-assured:rest-assured:4.4.0")
    testImplementation ("io.cucumber:cucumber-java:7.1.0")
    testImplementation ("io.cucumber:cucumber-junit:7.1.0")
    testImplementation ("io.cucumber:cucumber-testng:7.1.0")
    implementation ("org.apache.logging.log4j:log4j-core:2.17.0")
    implementation ("org.apache.logging.log4j:log4j-api:2.17.0")

}

tasks.test {
    useJUnitPlatform()
}