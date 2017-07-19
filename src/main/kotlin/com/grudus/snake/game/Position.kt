package com.grudus.snake.game

import java.awt.Dimension

data class Position(var x: Int, var y: Int) {
    constructor(position: Position) : this(position.x, position.y)

    companion object {
        fun middleOf(index: Index, size: Dimension) = Position(
                index.col * size.width + size.width / 2,
                index.row * size.height + size.height / 2
        )

        fun startOf(index: Index, size: Dimension) = Position(
                index.col * size.width,
                index.row * size.height
        )
    }

    operator fun plus(position: Position) = Position(x + position.x, y + position.y)
    operator fun minus(position: Position) = Position(x - position.x, y - position.y)
    operator fun dec() = Position(x - 1, y - 1)
    operator fun inc() = Position(x + 1, y + 1)
}