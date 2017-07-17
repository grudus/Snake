package com.grudus.snake.game.entity.snake

import com.grudus.snake.game.Index
import com.grudus.snake.game.Position
import com.grudus.snake.game.entity.Direction
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics

class SnakeHead(index: Index, direction: Direction) : SnakeTile(index, direction)  {
    private val color = Color.decode("#FF533D")!!

    override fun draw(g: Graphics, tileSize: Dimension) {
        g.color = color
        defaultFill(g, tileSize)
    }
}