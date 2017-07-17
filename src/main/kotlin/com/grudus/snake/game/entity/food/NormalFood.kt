package com.grudus.snake.game.entity.food

import com.grudus.snake.game.Index
import com.grudus.snake.game.Position
import com.grudus.snake.game.entity.snake.Snake
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.awt.image.ImageObserver

class NormalFood : Food("tomato.png") {
    override fun draw(g: Graphics, tileSize: Dimension, index: Index, imageObserver: ImageObserver) {
        val position = Position.middleOf(index, tileSize)
        g.color = Color.GREEN
        g.drawImage(image, position.x - tileSize.width / 2 + 3, position.y - tileSize.height / 2 + 3, tileSize.width - 6, tileSize.height - 6, imageObserver)
    }

    override fun interact(snake: Snake) {
        snake.increaseBody()
    }
}