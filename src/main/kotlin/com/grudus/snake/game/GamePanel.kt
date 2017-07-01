package com.grudus.snake.game

import com.grudus.snake.Window
import com.grudus.snake.game.entity.Direction
import com.grudus.snake.game.entity.Snake
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import javax.swing.JPanel
import javax.swing.Timer

class GamePanel(val window: Window) : JPanel(), KeyListener {
    private val backgroundColor = Color.decode("#AB987A")!!
    private val snake = Snake(Dimension(32, 32), Position(100, 100))
    private val normalSpeed = Speed.MEDIUM

    private val timer = Timer(normalSpeed.delayTime, {
        updateView()
    })


    init {
        background = backgroundColor
        addKeyListener(this)
        isFocusable = true
    }

    fun start() {
        timer.start()
        requestFocus()
    }

    fun stop() {
        timer.stop()
    }

    override fun paintComponent(g: Graphics?) {
        g!!.color = backgroundColor
        g.fillRect(0, 0, width, height)
        snake.draw(g)
    }


    fun updateView() {
        snake.updatePosition()
        repaint()
    }

    override fun keyTyped(e: KeyEvent?) {

    }

    override fun keyPressed(e: KeyEvent?) {
        when(e!!.keyCode) {
            KeyEvent.VK_UP -> snake.direction = Direction.UP
            KeyEvent.VK_DOWN -> snake.direction = Direction.DOWN
            KeyEvent.VK_LEFT -> snake.direction = Direction.LEFT
            KeyEvent.VK_RIGHT -> snake.direction = Direction.RIGHT
            KeyEvent.VK_SPACE -> timer.delay = Speed.FAST.delayTime
        }
    }

    override fun keyReleased(e: KeyEvent?) {
        when(e!!.keyCode) {
            KeyEvent.VK_SPACE -> timer.delay = normalSpeed.delayTime
        }
    }
}