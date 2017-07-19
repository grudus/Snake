package com.grudus.snake.game

enum class Speed(val delayTime: Int) {
    EXTRA_SLOW(512), SLOW(256), MEDIUM(128), FAST(64), EXTRA_FAST(32)
}