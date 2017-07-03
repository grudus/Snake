package com.grudus.snake.game.entity.food

import com.grudus.snake.game.Position
import com.grudus.snake.game.entity.snake.Snake
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics

class NormalFood : Food() {
    override fun draw(g: Graphics, tileSize: Dimension, position: Position) {
        g.color = Color.GREEN
        g.fillOval(position.x - tileSize.width / 2 + 3, position.y - tileSize.height / 2 + 3, tileSize.width - 6, tileSize.height - 6)
    }

    override fun interact(snake: Snake) {
        snake.increaseBody()
    }
}