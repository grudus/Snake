package com.grudus.snake.game.board.generator

import com.grudus.snake.game.board.Board
import com.grudus.snake.game.board.Field
import com.grudus.snake.meta.Config
import com.grudus.snake.utils.exceptions.ReadMapException
import com.grudus.snake.utils.hasExtension
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.File

class SnMapGenerator : MapGenerator<File> {
    private val log: Logger = LoggerFactory.getLogger(javaClass)

    override fun generate(data: File): Board {
        log.debug("Generate map for file ${data.absolutePath}")
        require(data.hasExtension(Config.MAP_EXTENSION)) { "File must have [.${Config.MAP_EXTENSION}] extension" }

        val board = data.readLines().takeIf { it.isNotEmpty() }
                ?.map { line -> line.split(Regex("\\s")) }
                ?.map { numbers ->
                    numbers.map { number ->
                        when (number) {
                            "1" -> Field.BLOCK
                            "0" -> Field.EMPTY
                            else -> throw ReadMapException("Found illegal character [$number]")
                        }
                    }
                } ?: throw ReadMapException("File must not me empty")

        return Board(board)
    }
}