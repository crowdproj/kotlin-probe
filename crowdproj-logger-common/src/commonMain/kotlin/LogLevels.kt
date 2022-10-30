package com.crowdproj.kotlin.probe.logger.common

/**
 * Уровени логирования по порядку от TRACE (index 0) до ERROR (index 4)
 */
enum class LogLevels {
    TRACE,
    DEBUG,
    INFO,
    WARN,
    ERROR,
    ;

//    companion object {
//        val DEFAULT: LogLevels = INFO
//    }
}
