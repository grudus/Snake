package com.grudus.snake.game.entity

import com.grudus.snake.game.Position
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics

class SnakeBody(position: Position, size: Dimension) : SnakeTile(position, size) {
    val color = Color.decode("#0F1626")!!

    override fun draw(g: Graphics) {
        g.color = color
        super.defaultFill(g)
    }

}