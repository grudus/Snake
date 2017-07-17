package com.grudus.snake.game

import java.awt.Dimension

data class Position(var x: Int, var y: Int) {
    constructor(position: Position) : this(position.x, position.y)

    companion object {
        fun middleOf(index: Index, tileSize: Dimension) = Position(
                index.col * tileSize.width + tileSize.width / 2,
                index.row * tileSize.height + tileSize.height / 2
        )
    }

    operator fun plus(position: Position) = Position(x + position.x, y + position.y)
    operator fun minus(position: Position) = Position(x - position.x, y - position.y)
}