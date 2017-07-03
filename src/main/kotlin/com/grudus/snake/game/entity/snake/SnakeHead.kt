package com.grudus.snake.game.entity.snake

import com.grudus.snake.game.Position
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics

class SnakeHead(position: Position, size: Dimension) : SnakeTile(position, size)  {
    private val color = Color.decode("#FF533D")!!

    override fun draw(g: Graphics) {
        g.color = color
        defaultFill(g)
    }
}