group = "com.ecom"
version = "0.0.1-SNAPSHOT"
description = "provision"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

dependencies {
    implementation(project(":core")) {
        exclude(group = "org.springframework.kafka", module = "spring-kafka")
    }
//	implementation("org.springframework.boot:spring-boot-starter-flyway")
    implementation("org.flywaydb:flyway-core:12.6.1")
    runtimeOnly("org.flywaydb:flyway-database-postgresql:12.6.1")
//	testImplementation("org.springframework.boot:spring-boot-starter-flyway-test")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
