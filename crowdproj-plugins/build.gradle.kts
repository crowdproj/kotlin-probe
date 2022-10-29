plugins {
    kotlin("jvm") version "1.7.20"
    `java-gradle-plugin`
    `kotlin-dsl`
}

group = "com.crowdproj.plugins"
version = "0.0.2"

repositories {
    mavenCentral()
}

gradlePlugin {
    plugins {
        create("crowdproj-platforms") {
            id = "crowdproj-platforms"
            implementationClass = "com.crowdproj.plugins.PlatformsPlugin"
//            version = project.version
        }
//        create("goodbye") {
//            id = "com.example.goodbye"
//            implementationClass = "com.example.goodbye.GoodbyePlugin"
//        }
    }
}

dependencies {
//    implementation(kotlin("multiplatform"))
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20")

    testImplementation(kotlin("test-junit"))
}
