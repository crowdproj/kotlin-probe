package com.crowdproj.plugins

import org.gradle.internal.impldep.junit.framework.TestCase.assertTrue
import org.gradle.internal.impldep.org.junit.Test
import org.gradle.testfixtures.ProjectBuilder

@Suppress("unused")
class PluginTest {
    @Test
    fun greetingTest() {
        val project = ProjectBuilder.builder().build()
        project.pluginManager.apply("crowdproj-platforms")

        assertTrue(project.pluginManager.hasPlugin("crowdproj-platform"))

//        assertNotNull(project.tasks.getByName("hello"));
    }
}
