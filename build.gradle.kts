plugins {
    id("java")
    id("war")
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
    implementation("org.springframework:spring-webmvc:5.3.14")
    implementation("org.springframework:spring-orm:5.3.14")
    implementation("org.hibernate:hibernate-core:5.6.3.Final")
    implementation("org.hibernate:hibernate-entitymanager:5.6.3.Final")
    implementation("org.postgresql:postgresql:42.3.1")
    implementation("org.thymeleaf:thymeleaf-spring5:3.0.14.RELEASE")

    compileOnly("javax.servlet:javax.servlet-api:4.0.1")

    testImplementation("org.springframework:spring-test:5.3.14")
    testImplementation(platform("org.junit:junit-bom:5.11.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}