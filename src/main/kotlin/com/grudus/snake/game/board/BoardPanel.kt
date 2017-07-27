package com.grudus.snake.game.board

import com.grudus.snake.LifeCyclePanel
import com.grudus.snake.game.Index
import com.grudus.snake.game.Speed
import com.grudus.snake.game.entity.Direction
import com.grudus.snake.game.entity.food.Foods
import com.grudus.snake.game.entity.snake.Snake
import com.grudus.snake.utils.Colors
import com.grudus.snake.utils.FontUtils
import com.grudus.snake.utils.GraphicsUtils
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.awt.event.KeyEvent
import java.awt.event.KeyEvent.*
import java.util.*
import javax.swing.Timer
import kotlin.properties.Delegates


class BoardPanel(private val board: Board, private val tileDimension: Dimension, private val initialSnakeSize: Int) : LifeCyclePanel() {
    private val initialSnakeSpeed = Speed.MEDIUM
    private val transparentBackground = Colors.TRANSPARENT_BLACK
    private val foods = Foods()
    private val movementQueue = LinkedList<Direction>()

    private val timer = Timer(initialSnakeSpeed.delayTime, { updateView() })

    private var snake: Snake by Delegates.notNull<Snake>()
    private var isGameEnded = false


    init {
        background = Colors.BOARD_BACKGROUND
    }

    override fun onInit() {
        isFocusable = true
        snake = Snake(Index(5, 5), initialSnakeSpeed, initialSnakeSize)
        updateSnakeSpeed()
        foods.newFoodAtRandom(board, snake)
        timer.restart()
        isGameEnded = false
    }

    override fun onPause() {
        timer.stop()
    }

    override fun onResume() {
        timer.start()
    }

    override fun onEnd() {
        isGameEnded = true
        timer.stop()
        foods.clean()
    }

    override fun paintComponent(g: Graphics?) {
        fillBackground(g!!, background)
        board.draw(g, tileDimension, this)
        snake.draw(g, tileDimension, this)
        foods.drawAll(g, tileDimension, this)
        if (isGameEnded)
            drawEndGame(g)
    }


    fun keyPressed(e: KeyEvent) {
        when (e.keyCode) {
            VK_UP, VK_W -> addMovement(Direction.UP)
            VK_DOWN, VK_S -> addMovement(Direction.DOWN)
            VK_LEFT, VK_A -> addMovement(Direction.LEFT)
            VK_RIGHT, VK_D -> addMovement(Direction.RIGHT)
            VK_SPACE -> timer.delay = Speed.EXTRA_FAST.delayTime
        }
    }

    fun keyReleased(e: KeyEvent) {
        when (e.keyCode) {
            VK_SPACE -> timer.delay = snake.currentSpeed.delayTime
        }
    }

    fun updateView() {
        snake.direction = movementQueue.poll() ?: snake.direction
        snake.updatePosition(board, foods)
        repaint()
    }

    fun updateSnakeSpeed() {
        timer.delay = snake.currentSpeed.delayTime
    }

    fun changeSize() {
        this.tileDimension.width = width / board.columns
        this.tileDimension.height = height / board.rows
    }

    private fun addMovement(direction: Direction) {
        if (movementQueue.size < 3)
            movementQueue.add(direction)
    }

    private fun drawEndGame(g: Graphics) {
        fillBackground(g, transparentBackground)
        g.color = Color.RED
        GraphicsUtils.drawCenteredString(g, "GAME OVER\nENTER to continue, ESC to go back", visibleRect, FontUtils.roboto(32))
    }

    private fun fillBackground(graphics: Graphics, color: Color) {
        graphics.color = color
        graphics.fillRect(0, 0, width, height)
    }
}
