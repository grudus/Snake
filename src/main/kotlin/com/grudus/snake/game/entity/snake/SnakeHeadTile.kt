package com.grudus.snake.game.entity.snake

import com.grudus.snake.game.Index
import com.grudus.snake.game.Position
import com.grudus.snake.game.entity.Direction
import com.grudus.snake.utils.ImageUtils
import java.awt.Component
import java.awt.Dimension
import java.awt.Graphics

class SnakeHeadTile(index: Index, direction: Direction) : SnakeTile(index, direction, ImageUtils.SNAKE_HEAD) {

    override fun draw(g: Graphics, size: Dimension, component: Component) {
        val position = Position.startOf(index, size)
        image.draw(component, g, size, position, direction)
    }
}