package com.grudus.snake.game.board.generator

import com.grudus.snake.game.Index
import com.grudus.snake.game.board.Board
import com.grudus.snake.game.board.Field

class DefaultMapGenerator : MapGenerator<Index> {

    override fun generate(data: Index): Board {
        val array = MutableList(data.row, { MutableList(data.column, { Field.EMPTY}) })

        for (row in 0..data.row - 1)
            (0..data.column - 1)
                    .filter { column -> isEdge(data, row, column) }
                    .forEach { column -> array[row][column] = Field.BLOCK }

        return Board(array)
    }

    private fun isEdge(size: Index, row: Int, column: Int) = row == 0 || column == 0 || row == size.row - 1 || column == size.column - 1

}