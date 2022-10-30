package com.crowdproj.kotlin.probe.logger.common

import com.crowdproj.kotlin.probe.eventmanager.common.IEventManager
import com.crowdproj.kotlin.probe.eventmanager.default.EventManagerGlobal
import kotlinx.datetime.Clock
import kotlin.reflect.KClass

class ProbeLogger(
    private val classifier: String,
    private val eventManager: IEventManager = EventManagerGlobal,
): IProbeLogger<LogEvent> {
    constructor(
        klass: KClass<*>,
        eventManager: IEventManager = EventManagerGlobal,
    ) : this(
        classifier = klass.simpleName ?: "(unnamed class)",
        eventManager = eventManager
    )

    override fun log(log: LogEvent) {
        eventManager.pub(log)
    }

    override fun log(
        level: LogLevels,
        message: String,
        data: Any?,
        tags: Set<String>?,
    ) = log(
        LogEvent(
            classifier = classifier,
            timeStamp = Clock.System.now(),
            threadName = "",
            message = message,
            level = level,
            tags = tags,
            data = data,
            sourceFile = null,
            sourceLine = null,
        )
    )
}
