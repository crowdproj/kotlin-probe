package com.crowdproj.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

@Suppress("unused")
class PlatformsPlugin: Plugin<Project> {
    override fun apply(project: Project) {
//        project.pluginManager.apply(KotlinMultiplatformPlugin::class.java)
        project.pluginManager.apply("org.jetbrains.kotlin.multiplatform")
        val extension = project.extensions.getByType(KotlinMultiplatformExtension::class.java)

        extension.apply {
            js(BOTH) {
                browser()
                nodejs()
            }
            jvm()
            linuxX64()
//    linuxArm64()
//    linuxArm32Hfp()
//    linuxMips32()
//    linuxMipsel32()
            ios()
            iosX64()
            iosArm64()
            iosSimulatorArm64()
            macosX64()
            macosArm64()
            tvos()
            tvosArm64()
            tvosSimulatorArm64()
            tvosX64()
            watchos()
            watchosArm32()
            watchosSimulatorArm64()
            watchosArm64()
            watchosX86()
            watchosX64()
//    wasm()
//    wasm32()
//    mingwX86()
            mingwX64()

        }
    }
}
