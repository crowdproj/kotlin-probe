package com.crowdproj.kotlin.probe.eventmanager.default

import com.crowdproj.kotlin.probe.eventmanager.common.IEvent
import com.crowdproj.kotlin.probe.eventmanager.common.IEventManager
import com.crowdproj.kotlin.probe.eventmanager.common.subscribe

inline fun <reified T : IEvent> IEventManager.mock(listEvent: MutableList<T>) =
    subscribe<T>("mock_event_manager") {
        onStart {}
//        filter { true }
        onEvent {
            listEvent.add(it)
        }
    }
