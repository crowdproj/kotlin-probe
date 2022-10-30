package com.crowdproj.kotlin.probe.logger.common

interface IProbeLogger<T : ILogEvent> {
    fun log(log: T)
    fun log(level: LogLevels, block: LogEventDsl.() -> Unit) {
        val logMessage = LogEventDsl().apply(block)
        log(
            level = level,
            message = logMessage.message,
            data = logMessage.data,
            tags = logMessage.tags,
        )
    }

    fun log(
        level: LogLevels, message: String,
        data: Any?,
        tags: Set<String>? = null,
    )

    fun trace(
        message: String,
        data: Any? = null,
        tags: Set<String>? = null,
    ) = log(
        level = LogLevels.TRACE,
        message = message,
        data = data,
        tags = tags,
    )
    fun trace(block: LogEventDsl.() -> Unit) = log(LogLevels.TRACE, block)


    fun debug(
        message: String,
        data: Any? = null,
        tags: Set<String>? = null,
    ) = log(
        level = LogLevels.DEBUG,
        message = message,
        data = data,
        tags = tags,
    )
    fun debug(block: LogEventDsl.() -> Unit) = log(LogLevels.DEBUG, block)

    fun info(
        message: String,
        data: Any? = null,
        tags: Set<String>? = null,
    ) = log(
        level = LogLevels.INFO,
        message = message,
        data = data,
        tags = tags,
    )
    fun info(block: LogEventDsl.() -> Unit) = log(LogLevels.INFO, block)

    fun warn(
        message: String,
        data: Any? = null,
        tags: Set<String>? = null,
    ) = log(
        level = LogLevels.WARN,
        message = message,
        data = data,
        tags = tags,
    )
    fun warn(block: LogEventDsl.() -> Unit) = log(LogLevels.WARN, block)

    fun error(
        message: String,
        data: Any? = null,
        tags: Set<String>? = null,
    ) = log(
        level = LogLevels.ERROR,
        message = message,
        data = data,
        tags = tags,
    )
    fun error(block: LogEventDsl.() -> Unit) = log(LogLevels.ERROR, block)

}
