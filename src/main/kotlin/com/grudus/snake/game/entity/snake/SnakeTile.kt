package com.grudus.snake.game.entity.snake

import com.grudus.snake.game.Index
import com.grudus.snake.game.entity.Direction
import com.grudus.snake.game.entity.Direction.RIGHT
import com.grudus.snake.utils.RotatedImageIcon
import java.awt.Component
import java.awt.Dimension

abstract class SnakeTile(var index: Index, var direction: Direction, protected val image: RotatedImageIcon) {
    var previousTileDirection: Direction = RIGHT

    abstract fun draw(g: java.awt.Graphics, size: Dimension, component: Component)

    fun changePosition(index: Index, direction: Direction) {
        this.index = index
        this.direction = direction
    }

    protected fun isCorner() = previousTileDirection != direction
}