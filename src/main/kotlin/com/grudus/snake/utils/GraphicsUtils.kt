package com.grudus.snake.utils

import java.awt.Font
import java.awt.Graphics
import java.awt.Rectangle

object GraphicsUtils {
    fun drawCenteredString(g: Graphics, text: String, rect: Rectangle, font: Font) {
        val lines = text.split(Regex("\\n"))
        val singleLineHeight = rect.height / lines.size
        lines.forEachIndexed {index, line ->
            drawSingleLine(g, line, Rectangle(rect.x, rect.y + (singleLineHeight) * index, rect.width, singleLineHeight), font)
        }
    }

    private fun drawSingleLine(g: Graphics, text: String, rect: Rectangle, font: Font) {
        val metrics = g.getFontMetrics(font)
        val x = rect.x + (rect.width - metrics.stringWidth(text)) / 2
        val y = rect.y + (rect.height - metrics.height) / 2 + metrics.ascent
        g.font = font
        g.drawString(text, x, y)

    }
}