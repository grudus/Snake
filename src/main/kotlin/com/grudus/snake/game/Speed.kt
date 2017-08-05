package com.grudus.snake.game

enum class Speed(val delayTime: Int, val label: String) {
    EXTRA_SLOW(512, "Turtle"), SLOW(256, "Slow"), MEDIUM(128, "Normal"), FAST(64, "Fast"), EXTRA_FAST(32, "Light speed")
    ;


    fun nextSpeed(): Speed = if (ordinal < values().size - 1) values()[ordinal + 1] else EXTRA_FAST
    fun previousSpeed(): Speed = if (ordinal > 0) values()[ordinal - 1] else EXTRA_SLOW
}