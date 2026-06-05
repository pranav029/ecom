plugins {
    java
    id("org.springframework.boot") version "4.0.6"
    id("io.spring.dependency-management") version "1.1.7"
}

allprojects {
    group = "com.ecom"
    version = "0.0.1-SNAPSHOT"
    repositories{
        mavenCentral()
    }
}

subprojects {
//    repositories {
//        mavenCentral()
//    }

//    Plugins in gradle don't get inherited by default, so we need to apply them in each subproject
    apply(plugin = "java")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
}

