package com.crowdproj.kotlin.probe.eventmanager.common

import kotlin.reflect.KClass

interface IEventManager {

    fun pub(event: IEvent)

    fun pub(block: () -> IEvent)

    suspend fun pubSusp(event: IEvent)

    fun <T : IEvent> sub(subscription: IEventSubscription<T>, clazz: KClass<T>)

}

inline fun <reified T : IEvent> IEventManager.subscribe(
    title: String,
    block: SubscribeEventManagerDsl<T>.() -> Unit,
) {
    val subscription = SubscribeEventManagerDsl<T>(title).apply(block).build()
    sub(subscription, T::class)
}
