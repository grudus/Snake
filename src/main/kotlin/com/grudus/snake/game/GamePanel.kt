package com.grudus.snake.game

import com.grudus.snake.Window
import com.grudus.snake.game.board.Board
import com.grudus.snake.game.board.BoardPanel
import com.grudus.snake.game.panel.ControlPanel
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.event.ComponentEvent
import java.awt.event.ComponentListener
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import javax.swing.JPanel
import kotlin.properties.Delegates

class GamePanel(private val window: Window) : JPanel(), KeyListener, ComponentListener {
    private var isPaused = false
    @Suppress("UNCHECKED_CAST")
    private val board: Board = window.getBoard()

    private val controlPanel = ControlPanel(this, Speed.MEDIUM)
    private var boardPanel by Delegates.notNull<BoardPanel>()

    init {
        boardPanel = BoardPanel(this, board, Dimension(30, 30))
        addKeyListener(this)
        addComponentListener(this)
        isFocusable = true

        val borderLayout = BorderLayout()
        super.setLayout(borderLayout)
        add(boardPanel, BorderLayout.CENTER)
        add(controlPanel, BorderLayout.NORTH)

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

    fun restart() {
        controlPanel.restart()
    }


    override fun keyReleased(e: KeyEvent?) {
        boardPanel.keyReleased(e!!)
    }

    fun  updateTime(speed: Speed) {
        controlPanel.updateSpeed(speed)
    }
    fun  updateSnakeSize(bodyLength: Int) {
        controlPanel.updateSnakeSize(bodyLength)
    }
    fun  addPoints(points: Int) {
        controlPanel.addPoints(points)
    }

    override fun keyTyped(e: KeyEvent?) {}
    override fun componentMoved(e: ComponentEvent?) {}
    override fun componentResized(e: ComponentEvent?) {
        boardPanel.changeSize()
    }
    override fun componentHidden(e: ComponentEvent?) {}
    override fun componentShown(e: ComponentEvent?) {}
}