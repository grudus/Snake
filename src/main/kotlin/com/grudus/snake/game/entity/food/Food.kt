package com.grudus.snake.game.entity.food

import com.grudus.snake.game.Index
import com.grudus.snake.game.Position
import com.grudus.snake.game.entity.snake.Snake
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Image
import java.awt.image.ImageObserver
import java.io.File
import javax.imageio.ImageIO

enum class Food(imagePath: String, val probability: Int) {
    NORMAL("tomato.png", 5) {
        override fun draw(g: Graphics, tileSize: Dimension, index: Index, imageObserver: ImageObserver) {
            val position = Position.middleOf(index, tileSize)
            g.drawImage(image, position.x - tileSize.width / 2 + 3, position.y - tileSize.height / 2 + 3, tileSize.width - 6, tileSize.height - 6, imageObserver)
        }

        override fun interact(snake: Snake) = snake.increaseBody()
    },
    BIG_FOOD("salad.png", 3) {
        override fun draw(g: Graphics, tileSize: Dimension, index: Index, imageObserver: ImageObserver) = defaultDraw(g, tileSize, index, imageObserver)

        override fun interact(snake: Snake) {
            snake.increaseBody()
            snake.increaseBody()
            snake.increaseBody()
        }
    },
    BEER("beer.png", 5) {
        override fun draw(g: Graphics, tileSize: Dimension, index: Index, imageObserver: ImageObserver) = defaultDraw(g, tileSize, index, imageObserver)

        override fun interact(snake: Snake) = snake.decreaseBody()

    },
    WATER("water.png", 3) {
        override fun draw(g: Graphics, tileSize: Dimension, index: Index, imageObserver: ImageObserver) = defaultDraw(g, tileSize, index, imageObserver)

        override fun interact(snake: Snake) = snake.increaseSpeed()
    },
    HAMBURGER("hamburger.png", 3) {
        override fun draw(g: Graphics, tileSize: Dimension, index: Index, imageObserver: ImageObserver) = defaultDraw(g, tileSize, index, imageObserver)

        override fun interact(snake: Snake) = snake.decreaseSpeed()
    }
    ;

    var image: Image? = null

    init {
        image = ImageIO.read(File(javaClass.classLoader.getResource("img/food/$imagePath").toURI()))
    }

    abstract fun interact(snake: Snake)
    abstract fun draw(g: Graphics, tileSize: Dimension, index: Index, imageObserver: ImageObserver)

    protected fun defaultDraw(g: Graphics, tileSize: Dimension, index: Index, imageObserver: ImageObserver) {
        val position = Position.middleOf(index, tileSize)
        g.drawImage(image, position.x - tileSize.width / 2, position.y - tileSize.height / 2, tileSize.width, tileSize.height, imageObserver)
    }
}