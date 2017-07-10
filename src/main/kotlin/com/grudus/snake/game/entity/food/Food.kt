package com.grudus.snake.game.entity.food

import com.grudus.snake.game.Position
import com.grudus.snake.game.entity.snake.Snake
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Image
import java.awt.image.ImageObserver

abstract class Food {
    protected var image: Image? = null

    abstract fun interact(snake: Snake)
    abstract fun draw(g: Graphics, tileSize: Dimension, position: Position, imageObserver: ImageObserver)
}