package com.grudus.snake.panels.selectable

import com.grudus.snake.LifeCyclePanel
import com.grudus.snake.utils.Colors
import com.grudus.snake.utils.FontUtils
import com.grudus.snake.utils.GraphicsUtils
import java.awt.Font
import java.awt.Graphics
import java.awt.Rectangle
import java.awt.event.KeyEvent
import java.awt.event.KeyEvent.*
import java.awt.event.KeyListener
import java.awt.font.TextAttribute

abstract class SelectablePanel(
        private val values: Collection<Displayable>,
        initialState: Int = 0,
        private val rules: SelectRules = SelectRules(Colors.MENU_ITEM_NORMAL, Colors.MENU_ITEM_SELECTED, FontUtils.roboto(24)),
        private val calculatePadding: (Int) -> Int = { height -> (height * 0.15).toInt() })
    : LifeCyclePanel(), KeyListener {

    private var currentState = initialState
    private val currentFont = rules.font.deriveFont(mapOf(Pair(TextAttribute.WEIGHT, TextAttribute.WEIGHT_EXTRA_LIGHT)))

    init {
        isFocusable = true
    }

    protected abstract fun onStateSelected(selected: Int)
    protected open fun onCancel() {}

    override fun onInit() {
        addKeyListener(this)
        requestFocus()
    }

    override fun paintComponent(g: Graphics?) {
        super.paintComponent(g!!)
        val padding = calculatePadding(height)
        val height: Int = ((height - 2 * padding) / values.size)

        values.forEachIndexed { index, state ->
            val font =
                    if (currentState == index) {
                        g.color = rules.selectedColor
                        currentFont.deriveFont(Font.BOLD)
                    } else {
                        g.color = rules.normalColor
                        currentFont.deriveFont(Font.PLAIN)
                    }
            GraphicsUtils.drawCenteredString(g, state.display(), Rectangle(0, padding + height * index, width, height), font)
        }
    }

    override fun keyPressed(e: KeyEvent?) {
        currentState = when (e?.keyCode) {
            VK_UP -> (--currentState % values.size)
            VK_DOWN -> (++currentState % values.size)
            else -> currentState
        }
        if (currentState < 0) {
            currentState = values.size - Math.abs(currentState)
        }
        repaint()
    }

    override fun keyReleased(e: KeyEvent?) {
        when(e?.keyCode) {
            VK_ESCAPE -> onCancel()
            VK_ENTER -> onStateSelected(currentState)
        }
    }

    override fun keyTyped(e: KeyEvent?) {}
}