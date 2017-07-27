package com.grudus.snake.game

import com.grudus.snake.LifeCyclePanel
import com.grudus.snake.event.EventBus
import com.grudus.snake.event.game.GameEventListener
import com.grudus.snake.event.game.NewGameEvent
import com.grudus.snake.event.game.PauseEvent
import com.grudus.snake.event.game.ResumeEvent
import com.grudus.snake.event.window.ShowMenuEvent
import com.grudus.snake.game.board.Board
import com.grudus.snake.game.board.BoardPanel
import com.grudus.snake.game.panel.ControlPanel
import com.grudus.snake.highscores.HighScores
import com.grudus.snake.highscores.dialog.NewHighScoreDialog
import com.grudus.snake.menu.MenuState
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.event.ComponentEvent
import java.awt.event.ComponentListener
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import kotlin.properties.Delegates

class GamePanel(board: Board, val highScores: HighScores) : LifeCyclePanel(), KeyListener, ComponentListener {
    private val initialSnakeSize = 5

    private val controlPanel = ControlPanel(this, Speed.MEDIUM, initialSnakeSize)
    private var boardPanel by Delegates.notNull<BoardPanel>()

    private var currentState = State.PLAYING

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
        currentState = State.PLAYING
        controlPanel.onInit()
        boardPanel.onInit()
    }

    override fun onPause() {
        currentState = State.PAUSE
        controlPanel.onPause()
        boardPanel.onPause()
    }

    override fun onResume() {
        currentState = State.PLAYING
        controlPanel.onResume()
        boardPanel.onResume()
    }

    override fun onEnd() {
        currentState = State.END
        boardPanel.onEnd()
        if (highScores.isScoreBeaten(controlPanel.getPoints())) {
            NewHighScoreDialog(controlPanel.getPoints()).showDialog()
        }
        controlPanel.onEnd()
    }

    override fun keyPressed(e: KeyEvent?) {
        when (e!!.keyCode) {
            KeyEvent.VK_ESCAPE -> handleEscape()
            KeyEvent.VK_ENTER -> if (currentState == State.END) EventBus.publish(NewGameEvent())
            else -> if (currentState == State.PLAYING) boardPanel.keyPressed(e)
        }
    }

    override fun keyReleased(e: KeyEvent?) = boardPanel.keyReleased(e!!)

    override fun componentResized(e: ComponentEvent?) = boardPanel.changeSize()

    fun updateTime(speed: Speed) {
        controlPanel.updateSpeed(speed)
        boardPanel.updateSnakeSpeed()
    }

    fun updateSnakeSize(bodyLength: Int) = controlPanel.updateSnakeSize(bodyLength)

    fun addPoints(points: Int) = controlPanel.addPoints(points)

    private fun handleEscape() {
        when (currentState) {
            State.PLAYING -> EventBus.publish(PauseEvent())
            State.PAUSE -> EventBus.publish(ResumeEvent())
            State.END -> EventBus.publish(ShowMenuEvent(MenuState.PLAY))
        }
    }

    override fun keyTyped(e: KeyEvent?) {}
    override fun componentMoved(e: ComponentEvent?) {}
    override fun componentHidden(e: ComponentEvent?) {}
    override fun componentShown(e: ComponentEvent?) {}

    private enum class State {
        PLAYING, PAUSE, END
    }
}