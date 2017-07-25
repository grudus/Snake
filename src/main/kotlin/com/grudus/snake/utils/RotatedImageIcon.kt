package com.grudus.snake.utils

import com.grudus.snake.game.Position
import com.grudus.snake.game.entity.Direction
import org.slf4j.LoggerFactory
import java.awt.*
import java.awt.geom.AffineTransform
import javax.swing.ImageIcon

class RotatedImageIcon(val imageIcon: ImageIcon) {
    private val log = LoggerFactory.getLogger(javaClass)

    constructor(image: Image) : this(ImageIcon(image))

    init {
        log.debug("Created rotated image instance")
    }


    fun draw(component: Component, g: Graphics, size: Dimension, position: Position, direction: Direction) {
        resizeImage(size)
        val radians = Math.toRadians(direction.degrees)
        val x = position.x.toDouble()
        val y = position.y.toDouble()

        val transform: AffineTransform

        if (size.width > size.height) {
            val alpha = size.getWidth() / size.getHeight()
            transform = AffineTransform.getTranslateInstance(x + (size.height - size.height / alpha) * direction.dx, y)
            transform.rotate(radians, size.getWidth() / 2, size.getHeight() / 2)
            transform.scale(1.0, alpha)
        }
        else {
            val alpha = size.getHeight() / size.getWidth()
            transform = AffineTransform.getTranslateInstance(x, y + (size.width - size.width / alpha) * direction.dy)
            transform.rotate(radians, size.getWidth() / 2, size.getHeight() / 2)
            transform.scale(alpha, 1.0)
        }
        val g2 =  g.create() as Graphics2D
        g2.drawImage(imageIcon.image, transform, component)
        g2.dispose()
    }

    private fun resizeImage(size: Dimension) {
        val width = size.width
        val height = size.height
        if (width == imageIcon.iconWidth && height == imageIcon.iconHeight)
            return
        val image = imageIcon.image.getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH)
        imageIcon.image = image
    }
}