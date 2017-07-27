package com.grudus.snake.game.entity


//Because position is calculated from top left corner, when snake goes down, it increases its 'y' position
enum class Direction(val dx: Int, val dy: Int, val degrees: Double) {
    UP(0, -1, 0.0),
    RIGHT(1, 0, 90.0),
    DOWN(0, 1, 180.0),
    LEFT(-1, 0, 270.0),
    ;

    val canChangeDirection: (Direction) -> Boolean = { it.successor.successor != this }

    val successor: Direction by lazy { values()[(ordinal + 1) % values().size] }
}
