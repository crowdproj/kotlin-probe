rootProject.name = "kotlin-probe"

pluginManagement {
    val kotlinVersion: String by settings
    val nexusStagingVersion: String by settings

    plugins {
        kotlin("multiplatform") version kotlinVersion
        kotlin("jvm") version kotlinVersion
        `maven-publish`
        id("org.jetbrains.dokka") version kotlinVersion
        id("io.codearte.nexus-staging") version nexusStagingVersion
    }
}

includeBuild("crowdproj-plugins")
include("crowdproj-eventmanager-common")
include("crowdproj-eventmanager-default")
