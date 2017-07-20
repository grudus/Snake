package com.grudus.snake.utils

import java.io.File
import javax.imageio.ImageIO

class ImageUtils private constructor() {
    private val snakeBodyCorner =  snakeIcon("body_corner.png")
    private val snakeBodyEnd =  snakeIcon("body_end.png")
    private val snakeHead =  snakeIcon("head.png")
    private val snakeNormal =  snakeIcon("body_normal.png")

    // TODO 20.07.2017 Remove memory leaks
    companion object {
        private val images = ImageUtils()
        val SNAKE_BODY_CORNER = images.snakeBodyCorner
        val SNAKE_BODY_END = images.snakeBodyEnd
        val SNAKE_HEAD = images.snakeHead
        val SNAKE_NORMAL = images.snakeNormal
    }
    
    private fun snakeIcon(imageName: String) = RotatedImageIcon(ImageIO.read(File(javaClass.classLoader.getResource("img/snake/$imageName").toURI())))
}