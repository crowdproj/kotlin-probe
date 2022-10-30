package com.crowdproj.kotlin.probe.eventmanager.default

import com.crowdproj.kotlin.probe.eventmanager.common.IEventManager

object EventManagerGlobal: IEventManager by DefaultEventManager()
