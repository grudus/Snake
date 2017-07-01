package com.grudus.snake.game.entity

import com.grudus.snake.game.Position
import java.awt.Dimension


enum class Direction {
    UP {
        override fun newPosition(oldPosition: Position, tile: Dimension)
                = Position(oldPosition.x, oldPosition.y - tile.height)
        override fun canChangeDirection(newDirection: Direction) = newDirection != DOWN
    },
    DOWN {
        override fun newPosition(oldPosition: Position, tile: Dimension)
                = Position(oldPosition.x, oldPosition.y + tile.height)
        override fun canChangeDirection(newDirection: Direction) = newDirection != UP
    },
    LEFT {
        override fun newPosition(oldPosition: Position, tile: Dimension)
                = Position(oldPosition.x - tile.width, oldPosition.y)
        override fun canChangeDirection(newDirection: Direction) = newDirection != RIGHT
    },
    RIGHT {
        override fun newPosition(oldPosition: Position, tile: Dimension)
                = Position(oldPosition.x + tile.width, oldPosition.y)
        override fun canChangeDirection(newDirection: Direction) = newDirection != LEFT
    }
    ;

    abstract fun newPosition(oldPosition: Position, tile: Dimension): Position
    abstract fun canChangeDirection(newDirection: Direction) : Boolean
}