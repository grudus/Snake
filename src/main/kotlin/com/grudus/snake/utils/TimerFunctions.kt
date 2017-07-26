package com.grudus.snake.utils

import javax.swing.Timer

fun Timer.runOnce() {
    this.isRepeats = false
    this.restart()
}