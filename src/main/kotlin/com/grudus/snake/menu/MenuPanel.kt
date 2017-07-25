package com.grudus.snake.menu


import com.grudus.snake.Window
import com.grudus.snake.utils.Colors
import com.grudus.snake.utils.FontUtils
import com.grudus.snake.utils.GraphicsUtils
import java.awt.Color
import java.awt.Font
import java.awt.Graphics
import java.awt.Rectangle
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import java.awt.font.TextAttribute
import javax.swing.JPanel


class MenuPanel(val window: Window) : JPanel(), KeyListener {
    private var currentState = 0
    private val roboto = FontUtils.roboto(24)

    private val backgroundColor = Colors.MENU_BACKGROUND
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
        super.paintComponent(g!!)
        val padding: Int = (height * 0.15).toInt()
        val height: Int = ((height - 2 * padding) / MenuState.values().size)
        MenuState.values().forEachIndexed { index, state ->
            val font =
                    if (currentState == index) {
                        g.color = selectedColor
                        roboto.deriveFont(Font.BOLD)
                    } else {
                        g.color = normalColor
                        roboto.deriveFont(Font.PLAIN)
                    }
            GraphicsUtils.drawCenteredString(g, state.toString(), Rectangle(0, padding + height * index, width, height), font)
        }
    }


    override fun keyTyped(e: KeyEvent?) {
    }

    override fun keyPressed(e: KeyEvent?) {
        changeCurrentState(e!!)
    }

    override fun keyReleased(e: KeyEvent?) {
        if (e!!.keyCode == KeyEvent.VK_ENTER)
            window.onStateChange(MenuState.values()[currentState])
    }

    private fun changeCurrentState(e: KeyEvent) {
        currentState = when (e.keyCode) {
            KeyEvent.VK_UP -> (--currentState % MenuState.values().size)
            KeyEvent.VK_DOWN -> (++currentState % MenuState.values().size)
            else -> currentState
        }
        if (currentState < 0) {
            currentState = MenuState.values().size - Math.abs(currentState)
        }
        repaint()
    }
}