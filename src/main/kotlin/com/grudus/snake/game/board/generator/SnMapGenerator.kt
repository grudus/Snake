package com.grudus.snake.game.board.generator

import com.grudus.snake.game.board.Board
import com.grudus.snake.game.board.Field
import java.io.File

class SnMapGenerator : MapGenerator<File> {

    override fun generate(data: File): Board {
        val board = data.readLines().map { line -> line.split(Regex("\\s")) }
                .map { numbers ->
                    numbers.map { number ->
                        when (number) {"1" -> Field.BLOCK
                            else -> Field.EMPTY
                        }
                    }.toTypedArray()
                }.toTypedArray()
        return Board(board)
    }
}