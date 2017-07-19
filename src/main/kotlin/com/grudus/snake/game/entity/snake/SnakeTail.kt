package com.grudus.snake.game.entity.snake

import com.grudus.snake.game.Index
import com.grudus.snake.game.Position
import com.grudus.snake.game.entity.Direction
import java.awt.Component
import java.awt.Dimension
import java.awt.Graphics

class SnakeTail(index: Index, direction: Direction) : SnakeTile(index, direction, "body_end.png") {
    override fun draw(g: Graphics, size: Dimension, component: Component) {
        val position = Position.startOf(index, size)
        image?.draw(component, g, size, position, direction)
    }
}