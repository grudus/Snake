package com.grudus.snake.game.entity


//Because position is calculated from top left corner, so when snake goes down it increases it's 'y' position
enum class Direction(val dx: Int, val dy: Int) {
    UP(0, -1) {
        override fun canChangeDirection(newDirection: Direction) = newDirection != DOWN
    },
    DOWN(0, 1) {
        override fun canChangeDirection(newDirection: Direction) = newDirection != UP
    },
    LEFT(-1, 0) {
        override fun canChangeDirection(newDirection: Direction) = newDirection != RIGHT
    },
    RIGHT(1, 0) {
        override fun canChangeDirection(newDirection: Direction) = newDirection != LEFT
    }
    ;

    abstract fun canChangeDirection(newDirection: Direction) : Boolean
}