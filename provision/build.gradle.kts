group = "com.ecom"
version = "0.0.1-SNAPSHOT"
description = "provision"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(25)
	}
}

dependencies {
	implementation(project(":core"))
//	implementation("org.springframework.boot:spring-boot-starter-flyway")
//	testImplementation("org.springframework.boot:spring-boot-starter-flyway-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
