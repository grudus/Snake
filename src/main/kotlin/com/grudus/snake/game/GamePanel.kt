package com.grudus.snake.game

import com.grudus.snake.event.EventBus
import com.grudus.snake.event.game.GameEventListener
import com.grudus.snake.event.game.PauseEvent
import com.grudus.snake.event.game.ResumeEvent
import com.grudus.snake.game.board.Board
import com.grudus.snake.game.board.BoardPanel
import com.grudus.snake.game.panel.ControlPanel
import com.grudus.snake.highscores.HighScores
import com.grudus.snake.highscores.dialog.NewHighScoreDialog
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.event.ComponentEvent
import java.awt.event.ComponentListener
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import javax.swing.JPanel
import kotlin.properties.Delegates

class GamePanel(board: Board, val highScores: HighScores) : JPanel(), KeyListener, ComponentListener, LifeCycle {
    private val initialSnakeSize = 5

    private val controlPanel = ControlPanel(this, Speed.MEDIUM, initialSnakeSize)
    private var boardPanel by Delegates.notNull<BoardPanel>()

    private var isPaused = false

    init {
        GameEventListener(this).startListening()
        boardPanel = BoardPanel(board, Dimension(30, 30), initialSnakeSize)
        addKeyListener(this)
        addComponentListener(this)

        super.setLayout(BorderLayout())
        add(boardPanel, BorderLayout.CENTER)
        add(controlPanel, BorderLayout.NORTH)
    }

    override fun onInit() {
        isFocusable = true
        requestFocus()
        controlPanel.onInit()
        boardPanel.onInit()
    }

    override fun onPause() {
        controlPanel.onPause()
        boardPanel.onPause()
    }

    override fun onResume() {
        controlPanel.onResume()
        boardPanel.onResume()
    }

    override fun onEnd() {
        boardPanel.onEnd()
        if (highScores.isScoreBeaten(controlPanel.getPoints())) {
            NewHighScoreDialog(controlPanel.getPoints()).showDialog()
        }
        controlPanel.onEnd()
    }

    override fun keyPressed(e: KeyEvent?) {
        when (e!!.keyCode) {
            KeyEvent.VK_ESCAPE -> togglePause()
        }
        if (!isPaused) boardPanel.keyPressed(e)
    }

    override fun keyReleased(e: KeyEvent?) = boardPanel.keyReleased(e!!)

    override fun componentResized(e: ComponentEvent?) = boardPanel.changeSize()

    fun updateTime(speed: Speed) {
        controlPanel.updateSpeed(speed)
        boardPanel.updateSnakeSpeed()
    }

    fun updateSnakeSize(bodyLength: Int) = controlPanel.updateSnakeSize(bodyLength)

    fun addPoints(points: Int) = controlPanel.addPoints(points)

    private fun togglePause() {
        if (isPaused) EventBus.publish(ResumeEvent())
        else EventBus.publish(PauseEvent())
        isPaused = !isPaused
    }

    override fun keyTyped(e: KeyEvent?) {}
    override fun componentMoved(e: ComponentEvent?) {}
    override fun componentHidden(e: ComponentEvent?) {}
    override fun componentShown(e: ComponentEvent?) {}
}