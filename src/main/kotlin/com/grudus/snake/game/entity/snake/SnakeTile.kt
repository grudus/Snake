package com.grudus.snake.game.entity.snake

import com.grudus.snake.game.Index
import com.grudus.snake.game.Position
import com.grudus.snake.game.entity.Direction
import javafx.geometry.Pos
import java.awt.Dimension
import java.awt.Graphics

abstract class SnakeTile(var index: Index, val direction: Direction) {
    abstract fun draw(g: java.awt.Graphics, tileSize: Dimension)

    protected fun defaultFill(g: Graphics, size: Dimension) {
        val position = Position.middleOf(index, size)
        g.fillRect(position.x - size.width / 2, position.y - size.height / 2, size.width, size.height)
    }

    fun changePosition(index: Index) {
        this.index = index
    }
}