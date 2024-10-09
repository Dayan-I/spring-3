plugins {
    id("java")
    id("war")
    id("org.springframework.boot") version "3.3.4"
    id ("io.spring.dependency-management") version "1.1.6"
}

group = "ru.vagapov.spring"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation ("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.postgresql:postgresql")
    compileOnly("javax.servlet:javax.servlet-api:4.0.1")

}

tasks.test {
    useJUnitPlatform()
}
