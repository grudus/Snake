package com.grudus.snake.game

import com.grudus.snake.game.board.Board
import com.grudus.snake.game.entity.Direction
import com.grudus.snake.game.entity.food.Foods
import com.grudus.snake.game.entity.snake.Snake
import com.grudus.snake.utils.FontUtils
import com.grudus.snake.utils.GraphicsUtils
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.awt.event.KeyEvent
import java.awt.event.KeyEvent.*
import java.util.*
import javax.swing.JPanel
import javax.swing.Timer

class BoardPanel(val gamePanel: GamePanel, val columns: Int, val rows: Int, val tileDimension: Dimension) : JPanel() {
    private val backgroundColor = Color(171, 152, 122)
    private val transparentBackground = Color(42, 42, 42, 200)
    private val board = Board(columns, rows)
    private val foods = Foods()
    private var snake = newSnake()
    private val normalSpeed = Speed.FAST
    private val roboto = FontUtils.roboto(32)
    
    private val movementQueue = LinkedList<Direction>()

    private val timer = Timer(normalSpeed.delayTime, {
        updateView()
    })


    init {
        background = backgroundColor
        isFocusable = true
    }

    fun restart() {
        snake = newSnake()
        foods.clean()
        startSnakeMovement()
    }

    fun startSnakeMovement() {
        foods.newFoodAtRandom(board, snake, tileDimension)
        if (!snake.isDead)
            timer.start()
    }

    fun stopSnakeMovement() {
        timer.stop()
    }

    override fun paintComponent(g: Graphics?) {
        fillBackground(g!!)
        board.draw(g, tileDimension)
        snake.draw(g)
        foods.drawAll(g, tileDimension)
        if (snake.isDead) {
            fillBackground(g, transparentBackground)
            g.color = Color.RED
            GraphicsUtils.drawCenteredString(g, "GAME OVER", visibleRect, roboto)
        }
    }

    private fun fillBackground(graphics: Graphics, color: Color = backgroundColor) {
        graphics.color = color
        graphics.fillRect(0, 0, width, height)
    }


    private fun newSnake() = Snake(tileDimension, Position(tileDimension.width * 5, tileDimension.height * 5), board, foods)

    fun keyReleased(e: KeyEvent) {
        when (e.keyCode) {
            VK_SPACE -> timer.delay = normalSpeed.delayTime
        }
    }

    fun updateView() {
        snake.direction = movementQueue.poll() ?: snake.direction
        snake.updatePosition()
        if (snake.isDead)
            timer.stop()
        repaint()
    }

    fun keyPressed(e: KeyEvent) {
        when (e.keyCode) {
            VK_UP, VK_W -> movementQueue.add(Direction.UP)
            VK_DOWN, VK_S -> movementQueue.add(Direction.DOWN)
            VK_LEFT, VK_A -> movementQueue.add(Direction.LEFT)
            VK_RIGHT, VK_D -> movementQueue.add(Direction.RIGHT)
            VK_SPACE -> timer.delay = Speed.EXTRA_FAST.delayTime
            VK_ENTER -> if (snake.isDead) restart()
        }
    }
}