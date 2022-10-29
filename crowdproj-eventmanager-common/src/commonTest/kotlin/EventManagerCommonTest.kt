package com.crowdproj.kotlin.probe.eventmanager.common

import kotlinx.coroutines.*
import kotlinx.coroutines.test.runTest
import kotlin.reflect.KClass
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class EventManagerCommonTest {
    @Test
    fun test() = runTest {
        withContext(Dispatchers.Default) {
            val events1 = mutableListOf<String>()
            val events2 = mutableListOf<String>()
            val eventManager = TestEventManager().apply {
                sub(
                    TestEventSubscription(
                        title = "test",
                        blockStart = { events1.add("start") },
                        blockFilter = { it.msg != "skip" },
                        blockCollect = { events1.add(it.msg) },
                    ),
                    TestEvent1::class
                )
                sub(
                    TestEventSubscription(
                        title = "test2",
                        blockStart = { events2.add("start2") },
                        blockFilter = { it.msg != "skip2" },
                        blockCollect = { events2.add(it.msg) },
                    ),
                    TestEvent2::class
                )
            }
            eventManager.pub(TestEvent1("msg1"))
            eventManager.pub(TestEvent2("msg12"))
            eventManager.pub(TestEvent1("skip"))
            eventManager.pub(TestEvent2("skip2"))
            eventManager.pub(TestEvent1("msg2"))
            eventManager.pub(TestEvent2("msg22"))

            assertEquals(listOf("start", "msg1", "msg2"), events1)
            assertEquals(listOf("start2", "msg12", "msg22"), events2)
        }
    }

    class TestEvent1(val msg: String) : IEvent {}
    class TestEvent2(val msg: String) : IEvent {}

    class TestEventManager : IEventManager {
        private val cctx = Dispatchers.Default + Job()
        private val scope = CoroutineScope(cctx)
        private val subscriptions: MutableMap<KClass<out IEvent>, MutableList<IEventSubscription<IEvent>>> = mutableMapOf()
        override fun pub(event: IEvent) {
            scope.launch {
                pubSusp(event)
            }
        }
        override fun pub(block: () -> IEvent) = pub(block())
        override suspend fun pubSusp(event: IEvent) {
            for ((c, ss) in subscriptions) {
                if (!c.isInstance(event)) continue
                for (s in ss) {
                    if (s.filter(event)) {
                        s.collect(event)
                    }
                }
            }
        }

        override fun <T : IEvent> sub(subscription: IEventSubscription<T>, clazz: KClass<T>) {
            val ss = subscriptions.getOrPut(clazz) { mutableListOf() }
            @Suppress("UNCHECKED_CAST")
            ss.add(subscription as IEventSubscription<IEvent>)
            scope.launch { subscription.start() }
        }
    }

    class TestEventSubscription<T: IEvent>(
        override val title: String,
        val blockStart: () -> Unit,
        val blockFilter: (T) -> Boolean,
        val blockCollect: (T) -> Unit,
    ) : IEventSubscription<T> {
        override suspend fun start() = blockStart()
        override suspend fun filter(event: T): Boolean = blockFilter(event)
        override suspend fun collect(event: T) = blockCollect(event)
    }
}
