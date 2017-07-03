package com.grudus.snake.game.entity.food

import com.grudus.snake.game.Position
import com.grudus.snake.game.entity.snake.Snake
import java.awt.Dimension
import java.awt.Graphics

abstract class Food {
    abstract fun interact(snake: Snake)
    abstract fun draw(g: Graphics, tileSize: Dimension, position: Position)
}