package com.crowdproj.kotlin.probe.eventmanager.common

class SubscribeEventManagerDsl<T : IEvent>(val title: String) {
    private var blockStart: suspend () -> Unit = {}
    private var blockFilterList: MutableList<suspend (T) -> Boolean> = mutableListOf()
    private var blockCollect: suspend (T) -> Unit = {}

    fun onStart(block: suspend () -> Unit) {
        blockStart = block
    }

    fun filter(block: (T) -> Boolean) {
        blockFilterList.add(block)
    }

    fun onEvent(block: suspend (T) -> Unit) {
        blockCollect = block
    }

    fun build() = EventSubscription(
        title = title,
        blockStart = blockStart,
        blockFilter = blockFilterList,
        blockCollect = blockCollect,
    )
}
