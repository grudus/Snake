package com.grudus.snake.game

import com.grudus.snake.game.board.Board
import com.grudus.snake.game.entity.Direction
import com.grudus.snake.game.entity.Snake
import com.grudus.snake.utils.FontUtils
import com.grudus.snake.utils.GraphicsUtils
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.awt.event.KeyEvent
import java.awt.event.KeyEvent.*
import javax.swing.JPanel
import javax.swing.Timer

class BoardPanel(val gamePanel: GamePanel, val columns: Int, val rows: Int, val tileDimension: Dimension) : JPanel() {
    private val backgroundColor = Color(171, 152, 122)
    private val transparentBackground = Color(42, 42, 42, 200)
    private val board = Board(columns, rows)
    private var snake = Snake(tileDimension, Position(tileDimension.width * 3, tileDimension.height * 3), board)
    private val normalSpeed = Speed.FAST
    private val roboto = FontUtils.roboto(32)

    private val timer = Timer(normalSpeed.delayTime, {
        updateView()
    })


    init {
        background = backgroundColor
        isFocusable = true
    }

    fun restart() {
        snake = Snake(tileDimension, Position(tileDimension.width * 3, tileDimension.height * 3), board)
        timer.start()
    }

    fun startSnakeMovement() {
        if (!snake.isDead)
            timer.start()
    }

    fun stopSnakeMovement() {
        timer.stop()
    }

    override fun paintComponent(g: Graphics?) {
        fillBackground(g!!)
        board.draw(g, tileDimension)
        if (snake.isDead) {
            fillBackground(g, transparentBackground)
            g.color = Color.RED
            GraphicsUtils.drawCenteredString(g, "GAME OVER", visibleRect, roboto)
        }
        snake.draw(g)
    }

    private fun fillBackground(graphics: Graphics, color: Color = backgroundColor) {
        graphics.color = color
        graphics.fillRect(0, 0, width, height)
    }


    fun updateView() {
        snake.updatePosition()
        if (snake.isDead)
            timer.stop()
        repaint()
    }

    fun keyPressed(e: KeyEvent) {
        when (e.keyCode) {
            VK_UP, VK_W -> snake.direction = Direction.UP
            VK_DOWN, VK_S -> snake.direction = Direction.DOWN
            VK_LEFT, VK_A -> snake.direction = Direction.LEFT
            VK_RIGHT, VK_D -> snake.direction = Direction.RIGHT
            VK_SPACE -> timer.delay = Speed.EXTRA_FAST.delayTime
            VK_ENTER -> if (snake.isDead) restart()
        }
    }

    fun keyReleased(e: KeyEvent) {
        when (e.keyCode) {
            VK_SPACE -> timer.delay = normalSpeed.delayTime
        }
    }
}