package com.grudus.snake.game.entity.snake

import com.grudus.snake.game.Index
import com.grudus.snake.game.Position
import com.grudus.snake.game.entity.Direction
import com.grudus.snake.game.entity.Direction.LEFT
import com.grudus.snake.game.entity.Direction.UP
import com.grudus.snake.utils.ImageUtils
import java.awt.Component
import java.awt.Dimension
import java.awt.Graphics

class SnakeBody(index: Index, direction: Direction) : SnakeTile(index, direction, ImageUtils.SNAKE_NORMAL) {

    private val cornerImage = ImageUtils.SNAKE_BODY_CORNER

    override fun draw(g: Graphics, size: Dimension, component: Component) {
        var position = Position.startOf(index, size)
        if (isCorner())
            cornerImage.draw(component, g, Dimension(size.width + 2, size.height + 2), --position, findCornerImageDirection())
        else
            image.draw(component, g, size, position, direction)
    }

    private fun findCornerImageDirection() =
            if (isTurningLeft()) direction
            else direction.successor


    private fun isTurningLeft() = isGoingFromUpToLeft()
            || (currentDegreesAreSmaller() && !isGoingFromLeftToUp())

    private fun isGoingFromUpToLeft() = direction == LEFT && previousTileDirection == UP
    private fun isGoingFromLeftToUp() = direction == UP && previousTileDirection == LEFT
    private fun currentDegreesAreSmaller() = previousTileDirection.degrees > direction.degrees

}
