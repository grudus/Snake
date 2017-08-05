package com.grudus.snake.game.board.generator

import com.grudus.snake.game.board.Board
import com.grudus.snake.game.board.Field
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.File

class SnMapGenerator : MapGenerator<File> {
    private val log: Logger = LoggerFactory.getLogger(javaClass)

    override fun generate(data: File): Board {
        log.debug("Generate map for file ${data.absolutePath}")

        val board = data.readLines().map { line -> line.split(Regex("\\s")) }
                .map { numbers ->
                    numbers.map { number ->
                        when (number) {"1" -> Field.BLOCK
                            else -> Field.EMPTY
                        }
                    }
                }
        return Board(board)
    }
}