package com.grudus.snake.game.board

import com.grudus.snake.game.GamePanel
import com.grudus.snake.game.Index
import com.grudus.snake.game.LifeCycle
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
import javax.swing.JPanel
import javax.swing.Timer



// TODO 23.07.2017 Add some kind of event listeners for communicate with control panel and pause/restart
class BoardPanel(private val gamePanel: GamePanel, private val board: Board, private val tileDimension: Dimension) : JPanel(), LifeCycle {
    private val backgroundColor = Colors.BOARD_BACKGROUND
    private val transparentBackground = Colors.TRANSPARENT_BLACK
    private val foods = Foods()
    private var snake = newSnake()
    private val roboto = FontUtils.roboto(32)

    
    private val movementQueue = LinkedList<Direction>()

    private val timer = Timer(snake.currentSpeed.delayTime, {
        updateView()
    })


    init {
        background = backgroundColor
        isFocusable = true
    }

    override fun stop() {
        timer.stop()
    }

    override fun restart() {
        gamePanel.restart()
        snake = newSnake()
        updateTime()
        foods.clean()
        startSnakeMovement()
    }

    fun startSnakeMovement() {
        foods.newFoodAtRandom(board, snake)
        if (!snake.isDead)
            timer.start()
    }

    fun stopSnakeMovement() {
        timer.stop()
    }

    override fun paintComponent(g: Graphics?) {
        fillBackground(g!!)
        board.draw(g, tileDimension, this)
        snake.draw(g, tileDimension, this)
        foods.drawAll(g, tileDimension, this)
        if (snake.isDead) {
            fillBackground(g, transparentBackground)
            g.color = Color.RED
            GraphicsUtils.drawCenteredString(g, "GAME OVER", visibleRect, roboto)
            println("Snake len: " + snake.bodyLength())
        }
    }

    private fun fillBackground(graphics: Graphics, color: Color = backgroundColor) {
        graphics.color = color
        graphics.fillRect(0, 0, width, height)
    }


    private fun newSnake() = Snake(Index(5, 5), Speed.MEDIUM, this)

    fun keyReleased(e: KeyEvent) {
        when (e.keyCode) {
            VK_SPACE -> timer.delay = snake.currentSpeed.delayTime
        }
    }

    fun updateTime() {
        timer.delay = snake.currentSpeed.delayTime
        gamePanel.updateTime(snake.currentSpeed)
    }

    fun updateView() {
        snake.direction = movementQueue.poll() ?: snake.direction
        snake.updatePosition(board, foods)
        gamePanel.updateSnakeSize(snake.bodyLength())
        if (snake.isDead)
            stop()
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

    fun changeSize() {
        this.tileDimension.width = width / board.columns
        this.tileDimension.height = height / board.rows
    }

    fun  addPoints(points: Int) {
        gamePanel.addPoints(points)
    }
}