package com.grudus.snake.game

import com.grudus.snake.Window
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Dimension
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import javax.swing.JPanel

class GamePanel(private val window: Window) : JPanel(), KeyListener {
    private val backgroundColor = Color.RED
    private var isPaused = false

    private val boardPanel = BoardPanel(this, 40, 30, Dimension(20, 20))

    init {
        background = backgroundColor
        addKeyListener(this)
        isFocusable = true

        val borderLayout = BorderLayout()
        super.setLayout(borderLayout)
        add(boardPanel, BorderLayout.CENTER)
        boardPanel.size = Dimension(200, 200)
    }

    fun start() {
        requestFocus()
        boardPanel.startSnakeMovement()
    }

    override fun keyPressed(e: KeyEvent?) {
        when(e!!.keyCode) {
            KeyEvent.VK_ESCAPE -> togglePause()
        }
        if (!isPaused) boardPanel.keyPressed(e)
    }

    private fun togglePause() {
        if (isPaused)
            boardPanel.startSnakeMovement()
        else
            boardPanel.stopSnakeMovement()
        isPaused = !isPaused
    }

    override fun keyReleased(e: KeyEvent?) {
        boardPanel.keyReleased(e!!)
    }


    override fun keyTyped(e: KeyEvent?) {}
}