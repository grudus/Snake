package com.grudus.snake.game

interface LifeCycle {
    fun onInit()
    fun onPause()
    fun onResume()
    fun onEnd()
}