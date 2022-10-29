package com.crowdproj.kotlin.probe.eventmanager.default.dsl

import com.crowdproj.kotlin.probe.eventmanager.common.IEventManager
import com.crowdproj.kotlin.probe.eventmanager.default.DefaultEventManager

/**
 * DSL контекст для создания менеджера событий
 */
inline fun eventManager(block: IEventManager.() -> Unit): IEventManager {
    val em = DefaultEventManager()
    em.apply(block)
    return em
}
