package com.grudus.snake.game.board.generator

import com.grudus.snake.game.board.Board

interface MapGenerator<T> {
    fun generate(data: T): Board
}