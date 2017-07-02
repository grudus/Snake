package com.grudus.snake.game

enum class Speed(val delayTime: Int) {
    SLOW(256), MEDIUM(128), FAST(64), EXTRA_FAST(32)
}