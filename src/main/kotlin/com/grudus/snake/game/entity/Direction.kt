package com.grudus.snake.game.entity


//Because position is calculated from top left corner, so when snake goes down it increases it's 'y' position
enum class Direction(val dx: Int, val dy: Int, val canChangeDirection: (Direction) -> Boolean) {
    UP(0, -1, {newDirection -> newDirection != DOWN}),
    DOWN(0, 1,  {newDirection -> newDirection != UP}),
    LEFT(-1, 0, {newDirection -> newDirection != RIGHT}),
    RIGHT(1, 0, {newDirection -> newDirection != LEFT})
}