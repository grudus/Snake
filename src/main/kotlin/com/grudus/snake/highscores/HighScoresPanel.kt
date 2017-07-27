package com.grudus.snake.highscores

import com.grudus.snake.LifeCyclePanel
import com.grudus.snake.event.EventBus.publish
import com.grudus.snake.event.window.ShowMenuEvent
import com.grudus.snake.menu.MenuState
import com.grudus.snake.utils.Colors
import com.grudus.snake.utils.FontUtils
import com.grudus.snake.utils.GraphicsUtils
import java.awt.Font
import java.awt.Graphics
import java.awt.Rectangle
import java.awt.event.KeyEvent
import java.awt.event.KeyListener

class HighScoresPanel(val highScores: HighScores) : LifeCyclePanel(), KeyListener {
    init {
        font = FontUtils.monospace(20, Font.BOLD)
        addKeyListener(this)
    }

    override fun onInit() {
        requestFocus()
        isFocusable = true
    }

    override fun paintComponent(g: Graphics?) {
        requestFocus()
        val sorted = highScores.scores.sortedByDescending { it.points }
        g!!.font = font
        g.color = Colors.HIGH_SCORES
        val padding: Int = (height * 0.15).toInt()
        val height: Int = ((height - 2 * padding) / sorted.size)
        sorted.forEachIndexed { index, state ->
           GraphicsUtils.drawCenteredString(g, createHighScoreItem(index+1, state), Rectangle(0, padding + height * index, width, height), font)
        }
    }

    override fun keyPressed(e: KeyEvent?) {
        if (e?.keyCode == KeyEvent.VK_ESCAPE) {
            publish(ShowMenuEvent(MenuState.HIGH_SCORES))
        }}

    private fun createHighScoreItem(position: Int, score: Score): String =
            "%-4s%-25s%-5s".format("$position.", score.name, score.points)


    override fun keyTyped(e: KeyEvent?) {}
    override fun keyReleased(e: KeyEvent?) {}

}
