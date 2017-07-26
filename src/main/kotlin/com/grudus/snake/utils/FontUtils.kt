package com.grudus.snake.utils

import java.awt.Font

object FontUtils {
    fun roboto(size: Int, style: Int = Font.PLAIN) = Font("Roboto", style, size)
    fun  monospace(size: Int,  style: Int = Font.PLAIN) = Font("monospaced", style, size)
}
