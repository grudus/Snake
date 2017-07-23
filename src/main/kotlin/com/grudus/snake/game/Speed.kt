package com.grudus.snake.game

enum class Speed(val delayTime: Int, val label: String) {
    EXTRA_SLOW(512, "Turtle"), SLOW(256, "Slow"), MEDIUM(128, "Normal"), FAST(64, "Fast"), EXTRA_FAST(32, "Light speed")
    ;
    companion object {
        fun findByDelayTime(delayTime: Int) = values().find { it.delayTime == delayTime }
    }
    fun nextSpeed(): Speed = findByDelayTime(delayTime / 2) ?: EXTRA_FAST
    fun previousSpeed(): Speed = findByDelayTime(delayTime * 2) ?: EXTRA_SLOW
}