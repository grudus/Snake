package com.grudus.snake.game.entity

import com.grudus.snake.game.Position
import java.awt.Dimension


enum class Direction {
    UP {
        override fun newPosition(oldPosition: Position, tile: Dimension)
                = Position(oldPosition.x, oldPosition.y - tile.height)
    },
    DOWN {
        override fun newPosition(oldPosition: Position, tile: Dimension)
                = Position(oldPosition.x, oldPosition.y + tile.height)
    },
    LEFT {
        override fun newPosition(oldPosition: Position, tile: Dimension)
                = Position(oldPosition.x - tile.width, oldPosition.y)
    },
    RIGHT {
        override fun newPosition(oldPosition: Position, tile: Dimension)
                = Position(oldPosition.x + tile.width, oldPosition.y)
    }
    ;

    abstract fun newPosition(oldPosition: Position, tile: Dimension): Position
}