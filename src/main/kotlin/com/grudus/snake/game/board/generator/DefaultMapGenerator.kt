package com.grudus.snake.game.board.generator

import com.grudus.snake.game.Index
import com.grudus.snake.game.board.Board
import com.grudus.snake.game.board.Field

class DefaultMapGenerator : MapGenerator<Index> {

    override fun generate(data: Index): Board {
        val array = (Array(data.row, {Array(data.column, { Field.EMPTY})}))
        array[0] = array[0].map { Field.BLOCK }.toTypedArray()
        array[array.lastIndex] = array[array.lastIndex].map { Field.BLOCK }.toTypedArray()

        for (i in 0..data.row - 1) {
            array[i][0] = Field.BLOCK
            array[i][data.column - 1]= Field.BLOCK
        }

        return Board(array)
    }
}