package com.grudus.snake.game.menu

import com.grudus.snake.game.Context
import java.awt.Color
import java.awt.Font
import java.awt.Graphics
import java.awt.Rectangle
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import java.awt.font.TextAttribute
import javax.swing.JPanel


class MenuPanel(val context: Context) : JPanel(), KeyListener {
    private var currentState = 0
    private val roboto = Font("Roboto", Font.PLAIN, 24)

    private val backgroundColor = Color.decode("#f5f5f5")
    private val normalColor = Color.decode("#242424")
    private val selectedColor = Color.decode("#ff533d")

    init {
        background = backgroundColor
        roboto.deriveFont(mapOf(Pair(TextAttribute.WEIGHT, TextAttribute.WEIGHT_EXTRA_LIGHT)))
        isFocusable = true
        requestFocus()
        addKeyListener(this)
    }


    override fun paintComponent(g: Graphics?) {
        super.paintComponent(g)
        g!!
        println("paint " + currentState)
        val padding: Int = (height * 0.15).toInt()
        val height: Int = ((height - 2 * padding) / MenuState.values().size)
        MenuState.values().forEachIndexed { index, state ->
            var font = roboto
            if (currentState == index) {
                g.color = selectedColor
                font = roboto.deriveFont(Font.BOLD)
            } else {
                g.color = normalColor
                font = roboto.deriveFont(Font.PLAIN)
            }
            drawCenteredString(g, state.toString(), Rectangle(0, padding + height * index, width, height), font)
        }
    }

    fun drawCenteredString(g: Graphics, text: String, rect: Rectangle, font: Font) {
        val metrics = g.getFontMetrics(font)
        val x = rect.x + (rect.width - metrics.stringWidth(text)) / 2
        val y = rect.y + (rect.height - metrics.height) / 2 + metrics.ascent
        g.font = font
        g.drawString(text, x, y)
    }

    override fun keyTyped(e: KeyEvent?) {
    }

    override fun keyPressed(e: KeyEvent?) {
        changeCurrentState(e!!)
        repaint()
    }

    override fun keyReleased(e: KeyEvent?) {
    }

    fun changeCurrentState(e: KeyEvent) {
        currentState = when (e.keyCode) {
            38 -> (--currentState % MenuState.values().size)
            40 -> (++currentState % MenuState.values().size)
            else -> currentState
        }
        if (currentState < 0) {
            currentState = MenuState.values().size - Math.abs(currentState)
        }
    }
}