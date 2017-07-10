package com.grudus.snake.game.board

import com.grudus.snake.game.Position
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.awt.image.ImageObserver
import java.io.File
import javax.imageio.ImageIO

enum class Field(val isBlocking: Boolean, imagePath: String? = null) {
    BLOCK(true, "wall.png") {
        override fun draw(g: Graphics, size: Dimension, position: Position, imageObserver: ImageObserver) {
            g.color = Color.BLACK
            g.drawImage(image!!, position.x - size.width / 2, position.y - size.height / 2, size.width, size.height, imageObserver)
        }
    },

    EMPTY(false) {
        override fun draw(g: Graphics, size: Dimension, position: Position, imageObserver: ImageObserver) {}
    }
    ;

    protected val image =
            if (imagePath != null)
                ImageIO.read(File(javaClass.classLoader.getResource("img/tile/$imagePath").toURI()))
            else
                null

    abstract fun draw(g: Graphics, size: Dimension, position: Position, imageObserver: ImageObserver)
}