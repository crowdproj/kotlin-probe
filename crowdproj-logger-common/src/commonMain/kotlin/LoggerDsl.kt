package com.crowdproj.kotlin.probe.logger.common

import com.crowdproj.kotlin.probe.eventmanager.common.IEventManager
import com.crowdproj.kotlin.probe.eventmanager.default.EventManagerGlobal
import kotlin.reflect.KClass

fun probeLogger(
    classifier: String,
    eventManager: IEventManager = EventManagerGlobal
) = ProbeLogger(classifier, eventManager)

fun probeLogger(
    klass: KClass<*>,
    eventManager: IEventManager = EventManagerGlobal
) = ProbeLogger(klass, eventManager)
