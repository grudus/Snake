package com.grudus.snake.game

data class Position(var x: Int, var y: Int) {
    operator fun plus(position: Position) = Position(x + position.x, y + position.y)
    operator fun minus(position: Position) = Position(x - position.x, y - position.y)
}