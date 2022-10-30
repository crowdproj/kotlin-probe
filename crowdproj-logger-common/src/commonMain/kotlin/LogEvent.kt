package com.crowdproj.kotlin.probe.logger.common

import kotlinx.datetime.Instant

/**
 * @param classifier - Идентификатор события. Используется в сообщениях
 */
data class LogEvent(
    override val classifier: String = "",
    override val timeStamp: Instant,
    override val threadName: String = "",
    override val message: String = "",
    override val level: LogLevels = LogLevels.INFO,
    override val tags: Set<String>? = null,
    override val data: Any? = null,
    override val sourceFile: String? = null,
    override val sourceLine: Int? = null,
) : ILogEvent

