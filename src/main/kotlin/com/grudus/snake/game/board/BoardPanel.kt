package com.grudus.snake.game

import com.grudus.snake.game.board.Board
import com.grudus.snake.game.entity.Direction
import com.grudus.snake.game.entity.Snake
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.awt.event.KeyEvent
import java.awt.event.KeyEvent.*
import javax.swing.JPanel
import javax.swing.Timer

class BoardPanel(val gamePanel: GamePanel, val columns: Int, val rows: Int, val tileDimension: Dimension) : JPanel() {
    private val backgroundColor = Color.decode("#AB987A")!!
    private val snake = Snake(tileDimension, Position(tileDimension.width*3, tileDimension.height * 3))
    private val board = Board(columns, rows)
    private val normalSpeed = Speed.FAST

    private val timer = Timer(normalSpeed.delayTime, {
        updateView()
    })


    init {
        background = backgroundColor
        isFocusable = true
    }

    fun start() {
        timer.start()
    }

    fun stop() {
        timer.stop()
    }

    override fun paintComponent(g: Graphics?) {
        g!!.color = backgroundColor
        g.fillRect(0, 0, width, height)
        board.draw(g, tileDimension)
        snake.draw(g)
    }


    fun updateView() {
        snake.updatePosition()
        repaint()
    }

    fun keyPressed(e: KeyEvent) {
        when (e.keyCode) {
            VK_UP, VK_W -> snake.direction = Direction.UP
            VK_DOWN, VK_S -> snake.direction = Direction.DOWN
            VK_LEFT, VK_A -> snake.direction = Direction.LEFT
            VK_RIGHT, VK_D -> snake.direction = Direction.RIGHT
            VK_SPACE -> timer.delay = Speed.EXTRA_FAST.delayTime
        }
    }

    fun keyReleased(e: KeyEvent) {
        when (e.keyCode) {
            VK_SPACE -> timer.delay = normalSpeed.delayTime
        }
    }
}