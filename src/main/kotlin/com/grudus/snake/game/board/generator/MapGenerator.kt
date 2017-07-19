package com.grudus.snake.game.board.generator

import com.grudus.snake.game.board.Board
import java.io.File

interface MapGenerator {
    fun generate(file: File): Board
}