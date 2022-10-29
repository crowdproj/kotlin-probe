package com.crowdproj.kotlin.probe.eventmanager.common

class EventSubscription<T: IEvent>(
    override val title: String,
    val blockStart: suspend () -> Unit,
    val blockFilter: List<suspend (T) -> Boolean>,
    val blockCollect: suspend (T) -> Unit,
) : IEventSubscription<T> {
    override suspend fun start() = blockStart()
    override suspend fun filter(event: T): Boolean = blockFilter.takeIf { it.isNotEmpty() }?.all { it(event) } ?: true
    override suspend fun collect(event: T) = blockCollect(event)
}
