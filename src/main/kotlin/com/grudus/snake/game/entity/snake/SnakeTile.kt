package com.grudus.snake.game.entity.snake

import com.grudus.snake.game.Index
import com.grudus.snake.game.entity.Direction
import com.grudus.snake.utils.RotatedImageIcon
import java.awt.Component
import java.awt.Dimension
import java.io.File
import javax.imageio.ImageIO

abstract class SnakeTile(var index: Index, var direction: Direction, imagePath: String? = null) {
    protected val image =
            if (imagePath != null)
                RotatedImageIcon(ImageIO.read(File(javaClass.classLoader.getResource("img/snake/$imagePath").toURI())))
            else
                null


    abstract fun draw(g: java.awt.Graphics, size: Dimension , component: Component)

    fun changePosition(index: Index, direction: Direction) {
        this.index = index
        this.direction = direction
    }
}