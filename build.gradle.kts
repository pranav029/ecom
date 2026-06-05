val bootModules = listOf("auth", "order", "product","inventory")
plugins {
    java
    id("org.springframework.boot") version "4.0.6" apply false
//    id("io.spring.dependency-management") version "1.1.7" apply false
}

allprojects {
    group = "com.ecom"
    version = "0.0.1-SNAPSHOT"
    repositories {
        mavenCentral()
    }
}

subprojects {
//    repositories {
//        mavenCentral()
//    }

//    Plugins in gradle don't get inherited by default, so we need to apply them in each subproject
    apply(plugin = "java")
//    apply(plugin = "io.spring.dependency-management")

    if (bootModules.contains(name)) {
        apply(plugin = "org.springframework.boot")
    }

    dependencies{
        implementation(platform("org.springframework.boot:spring-boot-dependencies:4.0.6"))
    }
}

