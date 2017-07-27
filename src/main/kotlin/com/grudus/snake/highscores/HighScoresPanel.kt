package com.grudus.snake.highscores

import com.grudus.snake.utils.Colors
import com.grudus.snake.utils.FontUtils
import com.grudus.snake.utils.GraphicsUtils
import java.awt.Font
import java.awt.Graphics
import java.awt.Rectangle
import javax.swing.JPanel

class HighScoresPanel(val highScores: HighScores) : JPanel() {

    init {
        font = FontUtils.monospace(20, Font.BOLD)
    }

    override fun paintComponent(g: Graphics?) {
        val sorted = highScores.scores.sortedByDescending { it.points }
        g!!.font = font
        g.color = Colors.HIGH_SCORES
        val padding: Int = (height * 0.15).toInt()
        val height: Int = ((height - 2 * padding) / sorted.size)
        sorted.forEachIndexed { index, state ->
           GraphicsUtils.drawCenteredString(g, createHighScoreItem(index+1, state), Rectangle(0, padding + height * index, width, height), font)
        }
    }

    private fun createHighScoreItem(position: Int, score: Score): String =
            "%-4s%-25s%-5s".format("$position.", score.name, score.points)

}