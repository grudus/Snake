package com.grudus.snake

import org.slf4j.LoggerFactory
import javax.swing.JPanel

open class LifeCyclePanel : JPanel(), LifeCycle {
    protected val log = LoggerFactory.getLogger(javaClass)!!
    override fun onInit() = log.warn("On init not implemented")
    override fun onPause() = log.warn("On pause not implemented")
    override fun onResume() = log.warn("On resume not implemented")
    override fun onEnd() = log.warn("On end not implemented")
}