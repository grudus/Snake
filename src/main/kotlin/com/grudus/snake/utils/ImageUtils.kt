package com.grudus.snake.utils

import java.io.File
import javax.imageio.ImageIO

object ImageUtils {
    val SNAKE_BODY_CORNER =  snakeIcon("body_corner.png")
    val SNAKE_BODY_END =  snakeIcon("body_end.png")
    val SNAKE_HEAD =  snakeIcon("head.png")
    val SNAKE_NORMAL =  snakeIcon("body_normal.png")

    private fun snakeIcon(imageName: String) = RotatedImageIcon(ImageIO.read(File(javaClass.classLoader.getResource("img/snake/$imageName").toURI())))
}
