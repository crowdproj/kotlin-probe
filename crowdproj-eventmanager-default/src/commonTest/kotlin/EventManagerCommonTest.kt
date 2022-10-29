package com.crowdproj.kotlin.probe.eventmanager

import com.crowdproj.kotlin.probe.eventmanager.common.IEvent
import com.crowdproj.kotlin.probe.eventmanager.common.subscribe
import com.crowdproj.kotlin.probe.eventmanager.default.dsl.eventManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class EventManagerCommonTest {
    @Test
    fun test() = runTest {
        withContext(Dispatchers.Default) {
            val events1 = mutableListOf<String>()
            val events2 = mutableListOf<String>()
            val eventManager = eventManager {
                subscribe<TestEvent1>("test1") {
                    onStart { events1.add("start") }
                    filter { it.msg != "skip" }
                    onEvent { events1.add(it.msg) }
                }
                subscribe<TestEvent2>("test2") {
                    onStart { events2.add("start2") }
                    filter { it.msg != "skip2" }
                    onEvent { events2.add(it.msg) }
                }
            }

            delay(100)
            eventManager.pubSusp(TestEvent1("msg1"))
            eventManager.pub(TestEvent2("msg12"))
            eventManager.pub(TestEvent1("skip"))
            eventManager.pub(TestEvent2("skip2"))
            eventManager.pub(TestEvent1("msg2"))
            eventManager.pub(TestEvent2("msg22"))

            delay(100)

            assertEquals(listOf("start", "msg1", "msg2"), events1)
            assertEquals(listOf("start2", "msg12", "msg22"), events2)
        }
    }

    data class TestEvent1(val msg: String) : IEvent {}
    data class TestEvent2(val msg: String) : IEvent {}
}
