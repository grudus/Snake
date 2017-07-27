package com.grudus.snake.highscores.dialog

import com.grudus.snake.event.EventBus
import com.grudus.snake.event.highscores.NewHighScoreEvent
import com.grudus.snake.highscores.Score
import com.grudus.snake.utils.Colors
import com.grudus.snake.utils.FontUtils
import com.grudus.snake.utils.GraphicsUtils
import java.awt.Font
import java.awt.Graphics
import java.awt.Rectangle
import java.awt.event.KeyEvent
import javax.swing.JPanel


class NewHighScorePanel(val score: Int, val dialog: NewHighScoreDialog) : JPanel() {
    private val message = "Gratulations! You've scored new record!\nPlease, enter your name:"
    private val nameLimit = 20
    private var userName = ""

    init {
        background = Colors.MENU_BACKGROUND
        font = FontUtils.roboto(16)
    }

    override fun paintComponent(g: Graphics?) {
        g!!.font = FontUtils.roboto(16, Font.BOLD)
        g.color = Colors.MENU_BACKGROUND
        g.fillRect(0, 0, width, height)

        drawMessage(g)
        drawLines(g)
    }

    private fun drawLines(g: Graphics) {
        g.color = Colors.HIGH_SCORES
        g.font = FontUtils.monospace(24, Font.BOLD)
        val padding = 60
        val space = 6
        val singleLineWidth = (width - 2 * padding) / nameLimit - space
        for (i in 0..nameLimit) {
            val x = padding + i * (space + singleLineWidth)
            if (i < userName.length) {
                g.drawString(userName[i].toString(), x, height - 65)
            }
            g.fillRect(x, height - 60, singleLineWidth, 4)
        }
    }

    private fun drawMessage(g: Graphics) {
        g.color = Colors.HIGH_SCORES
        val textRect = Rectangle(0, 20, width, (0.4 * height).toInt())
        GraphicsUtils.drawCenteredString(g, message, textRect, g.font)
    }

    fun keyPressed(e: KeyEvent) {
        when (e.keyCode) {
            KeyEvent.VK_ENTER -> save()
            KeyEvent.VK_BACK_SPACE -> removeChar()
            KeyEvent.VK_ESCAPE -> dispose()
            else -> addToUserName(e.keyChar)
        }
        repaint()
    }

    private fun dispose() {
        dialog.dispose()
    }

    private fun save() {
        if (userName.isEmpty()) userName = "Anonymous"
        EventBus.publish(NewHighScoreEvent(Score(userName, score)))
        dispose()
    }

    private fun addToUserName(char: Char) {
        if (char.isDefined() && userName.length <= nameLimit)
            userName += char
    }

    private fun removeChar() {
        if (userName.isNotEmpty())
            userName = userName.substring(0, userName.length - 1)
    }

}