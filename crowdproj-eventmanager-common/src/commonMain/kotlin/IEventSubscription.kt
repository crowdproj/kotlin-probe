package com.crowdproj.kotlin.probe.eventmanager.common

interface IEventSubscription<T : IEvent> {
    val title: String
    suspend fun start()
    suspend fun filter(event: T): Boolean
    suspend fun collect(event: T)
}
