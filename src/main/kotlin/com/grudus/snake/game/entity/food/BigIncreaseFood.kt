package com.grudus.snake.game.entity.food

import com.grudus.snake.game.Position
import com.grudus.snake.game.entity.snake.Snake
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.awt.image.ImageObserver

class BigIncreaseFood : Food("salad.png") {
    override fun interact(snake: Snake) {
        snake.increaseBody()
        snake.increaseBody()
        snake.increaseBody()
    }

    override fun draw(g: Graphics, tileSize: Dimension, position: Position, imageObserver: ImageObserver) {
        g.color = Color.BLUE
        g.drawImage(image, position.x - tileSize.width / 2, position.y - tileSize.height / 2, tileSize.width, tileSize.height, imageObserver)
    }

}

