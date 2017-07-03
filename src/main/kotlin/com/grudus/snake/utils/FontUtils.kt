package com.grudus.snake.utils

import java.awt.Font

class FontUtils {
    companion object {
         fun roboto(size: Int, style: Int = Font.PLAIN) = Font("Roboto", style, size)
    }
}