package com.grudus.snake.game.entity.snake

import com.grudus.snake.game.Index
import com.grudus.snake.game.Position
import com.grudus.snake.game.entity.Direction
import com.grudus.snake.game.entity.Direction.LEFT
import com.grudus.snake.game.entity.Direction.UP
import com.grudus.snake.utils.RotatedImageIcon
import java.awt.Component
import java.awt.Dimension
import java.awt.Graphics
import java.io.File
import javax.imageio.ImageIO

class SnakeBody(index: Index, direction: Direction) : SnakeTile(index, direction, "body_normal.png") {

    private val cornerImage = RotatedImageIcon(ImageIO.read(File(javaClass.classLoader.getResource("img/snake/body_corner.png").toURI())))

    override fun draw(g: Graphics, size: Dimension, component: Component) {
        var position = Position.startOf(index, size)
        if (isCorner())
            cornerImage.draw(component, g, Dimension(size.width + 2, size.height + 2), --position, findCornerImageDirection())
        else
            image?.draw(component, g, size, position, direction)
    }

    private fun findCornerImageDirection(): Direction {
        val isTurningLeft = isGoingFromUpToLeft()
                || (currentDegreesAreSmaller() && !isGoingFromLeftToUp())

        return if (isTurningLeft) direction else Direction.findByDegrees((direction.degrees + 90) % 360)!!

    }

    private fun isGoingFromUpToLeft() = direction == LEFT && previousTileDirection == UP
    private fun isGoingFromLeftToUp() = direction == UP && previousTileDirection == LEFT
    private fun currentDegreesAreSmaller() = previousTileDirection.degrees > direction.degrees

}