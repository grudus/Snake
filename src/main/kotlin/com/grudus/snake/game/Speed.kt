package com.grudus.snake.game

enum class Speed(val delayTime: Int) {
    EXTRA_SLOW(512), SLOW(256), MEDIUM(128), FAST(64), EXTRA_FAST(32)
    ;
    companion object {
        fun findByDelayTime(delayTime: Int) = values().find { it.delayTime == delayTime }
    }
    fun nextSpeed(): Speed = findByDelayTime(delayTime / 2) ?: EXTRA_FAST
    fun previousSpeed(): Speed = findByDelayTime(delayTime * 2) ?: EXTRA_SLOW
}