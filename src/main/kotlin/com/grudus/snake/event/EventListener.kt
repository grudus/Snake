package com.grudus.snake.event

import org.slf4j.Logger
import org.slf4j.LoggerFactory

abstract class EventListener {
    protected val log: Logger = LoggerFactory.getLogger(javaClass)

    abstract fun startListening()
}