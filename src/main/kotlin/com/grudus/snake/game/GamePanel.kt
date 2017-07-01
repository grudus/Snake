package com.grudus.snake.game

import com.grudus.snake.Window
import com.grudus.snake.game.entity.Snake
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import javax.swing.JPanel
import javax.swing.Timer

class GamePanel(val window: Window) : JPanel() {

    private val backgroundColor = Color.decode("#AB987A")!!
    private val snake = Snake(Dimension(32, 32), Position(100, 100))
    private val timer = Timer(32, {
        updateView()
    })

    init {
        background = backgroundColor
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
        snake.draw(g)
    }


    fun updateView() {
        println("Updating")
        snake.updatePosition()
        repaint()
    }
}