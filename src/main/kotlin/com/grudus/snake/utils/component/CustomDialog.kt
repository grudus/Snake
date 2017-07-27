package com.grudus.snake.utils.component

import java.awt.Dimension
import java.awt.Toolkit
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import javax.swing.JDialog
import javax.swing.JFrame
import javax.swing.WindowConstants

open class CustomDialog(barTitle: String, initialSize: Dimension):
        JDialog(null as JFrame?, barTitle, true), KeyListener {
    init {
        size = initialSize
        preferredSize = initialSize
        defaultCloseOperation = WindowConstants.DISPOSE_ON_CLOSE
        centerOnTheScreen()
        isFocusable = true
    }

    private fun centerOnTheScreen() {
        val screenSize = Toolkit.getDefaultToolkit().screenSize
        this.setLocation(screenSize.width / 2 - width / 2, screenSize.height / 2 - height / 2)
    }


    fun showDialog() {
        addKeyListener(this)
        isVisible = true
        requestFocus()
    }

    override fun keyTyped(e: KeyEvent?) {}
    override fun keyPressed(e: KeyEvent?) {}
    override fun keyReleased(e: KeyEvent?) {}
}
