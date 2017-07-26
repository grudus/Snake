package com.grudus.snake.settings

interface ChangingSettings {
    fun updateSettings()
    fun isChangePossible(): Boolean
}