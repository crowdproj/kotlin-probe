package com.crowdproj.kotlin.probe.logger.common

class LogEventDsl() {
    var message: String = ""
        private set
    var data: Any? = null
        private set
    private val _tags = mutableSetOf<String>()
    val tags: Set<String>
        get() = _tags.toSet()

    fun message(msg: String) {
        message = msg
    }

    fun message(block: () -> String) {
        message = block()
    }

    fun data(dt: Any?) {
        data = dt
    }

    fun data(block: () -> Any?) {
        data = block()
    }

    fun tags(vararg tgs: String) {
        _tags.addAll(tgs)
    }

    fun tags(tgs: Iterable<String>) {
        _tags.addAll(tgs)
    }

    fun tag(tg: String) {
        _tags.add(tg)
    }

    fun tags(block: () -> Set<String>) {
        _tags.addAll(block())
    }

}
