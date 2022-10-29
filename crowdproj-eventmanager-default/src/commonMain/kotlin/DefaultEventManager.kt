package com.crowdproj.kotlin.probe.eventmanager.default

import com.crowdproj.kotlin.probe.eventmanager.common.IEvent
import com.crowdproj.kotlin.probe.eventmanager.common.IEventManager
import com.crowdproj.kotlin.probe.eventmanager.common.IEventSubscription
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlin.reflect.KClass

class DefaultEventManager : IEventManager {
    private val cctx = Dispatchers.Default + Job() + CoroutineName("DefaultEventManager")
    private val scope = CoroutineScope(cctx)

    private val events = MutableSharedFlow<IEvent>(
        extraBufferCapacity = 1024, onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    override fun pub(event: IEvent) {
        scope.launch {
            pubSusp(event)
        }
    }

    override fun pub(block: () -> IEvent) = pub(block())
    override suspend fun pubSusp(event: IEvent) {
        events.emit(event)
    }

    override fun <T : IEvent> sub(subscription: IEventSubscription<T>, clazz: KClass<T>) {
        scope.launch {
            events.onSubscription { println("Subscription to '${subscription.title}'") }
                .onStart { subscription.start() }
                .filter { event -> clazz.isInstance(event) }
                .mapNotNull { event ->
                    @Suppress("UNCHECKED_CAST")
                    event as? T
                }
                .filter { event -> subscription.filter(event) }.collect { event ->
                    subscription.collect(event)
                }
        }
    }
}
