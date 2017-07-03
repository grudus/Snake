package com.grudus.snake.game

data class Position(var x: Int, var y: Int) {
    constructor(position: Position) : this(position.x, position.y)

    operator fun plus(position: Position) = Position(x + position.x, y + position.y)
    operator fun minus(position: Position) = Position(x - position.x, y - position.y)
}