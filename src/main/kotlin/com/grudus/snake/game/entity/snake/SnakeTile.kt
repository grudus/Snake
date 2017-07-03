package com.grudus.snake.game.entity.snake

import com.grudus.snake.game.Position
import java.awt.Graphics

abstract class SnakeTile(val position: Position, val size: java.awt.Dimension) {
    abstract fun draw(g: java.awt.Graphics)

    protected fun defaultFill(g: Graphics) {
        g.fillRect(position.x - size.width / 2, position.y - size.height / 2, size.width, size.height)
    }

    fun changePosition(position: Position) {
        this.position.x = position.x
        this.position.y = position.y
    }

    fun changeX(x: Int) {
        position.x += x
    }

    fun changeY(y: Int) {
        position.y += y
    }
}