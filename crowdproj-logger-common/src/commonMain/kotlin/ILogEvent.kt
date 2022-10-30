package com.crowdproj.kotlin.probe.logger.common

import com.crowdproj.kotlin.probe.eventmanager.common.IEvent
import kotlinx.datetime.Instant

interface ILogEvent : IEvent {
    val classifier: String
    val timeStamp: Instant
    val threadName: String
    val message: String
    val level: LogLevels
    val tags: Set<String>?
    val data: Any?
    val sourceFile: String?
    val sourceLine: Int?
}
