package com.crowdproj.kotlin.probe.logger.common

import com.crowdproj.kotlin.probe.eventmanager.common.IEvent
import com.crowdproj.kotlin.probe.eventmanager.default.dsl.eventManager
import com.crowdproj.kotlin.probe.eventmanager.default.mock
import kotlinx.coroutines.*
import kotlinx.coroutines.test.runTest
import kotlin.js.JsName
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
internal class LoggerCommonTest {

    @Test
    @JsName("pub_message_from_eventManager")
    fun `pub message from eventManager`() = runTest {
        withContext(Dispatchers.Default) {
            val events = mutableListOf<LogEvent>()
            val eventManager = eventManager {
                mock(events)
            }

            delay(100)
            eventManager.pub(LogEvent(msg = "ok"))

            delay(100)
            assertTrue(events.isNotEmpty())
            assertEquals(1, events.size)
            println("MESSAGE: ${events.first()}")
        }
    }

    @Test
    @JsName("init_logger")
    fun `init logger`() = runTest {
        withContext(Dispatchers.Default) {
            val events = mutableListOf<ILogEvent>()
            val eventManager = eventManager {
                mock(events)
            }
            val logger = probeLogger(this@LoggerCommonTest::class, eventManager)

            delay(100)

            logger.error {
                message { "test message 1" }
                data { dataLog }
            }
            logger.error {
                message { "test message 2" }
                data { dataLog }
            }
            delay(100)
            assertTrue(events.isNotEmpty())
            assertEquals(2, events.size)
        }
    }

    data class LogEvent(val msg: String): IEvent
    data class LogData(val x: String): IEvent

    private val dataLog = LogData(
        x = "123"
    )

}
