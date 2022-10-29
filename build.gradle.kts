import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform") apply false
    id("signing")
    id("io.codearte.nexus-staging")
}

group = "com.crowdproj"
version = "0.0.0"

repositories {
    mavenCentral()
}

subprojects {
    group = rootProject.group
    version = rootProject.version

    repositories {
        mavenCentral()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }
}

tasks {
    closeAndReleaseRepository {
//        dependsOn(publish)
    }
}
