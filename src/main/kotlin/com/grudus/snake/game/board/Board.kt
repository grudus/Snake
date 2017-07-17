package com.grudus.snake.game.board

import com.grudus.snake.game.Index
import com.grudus.snake.game.Position
import java.awt.Dimension
import java.awt.Graphics
import java.awt.image.ImageObserver

class Board(val columns: Int, val rows: Int) {
    private val board = Array(rows, { Array(columns, { Field.EMPTY }) })


    init {
        board[0] = board[0].map { Field.BLOCK }.toTypedArray()
        board[rows - 1] = board[rows - 1].map { Field.BLOCK }.toTypedArray()
        board.forEach { row ->
            row[0] = Field.BLOCK
            row[columns - 1] = Field.BLOCK
        }
    }

    fun isAccessible(row: Int, column: Int) = !board[row][column].isBlocking

    fun draw(g: Graphics, tileDimension: Dimension, imageObserver: ImageObserver) {
        for (row in 0..rows - 1)
            for (col in 0..columns - 1)
                board[row][col].draw(g, tileDimension, Position(col * tileDimension.width + tileDimension.width / 2, row * tileDimension.height + tileDimension.height / 2), imageObserver)

    }

    fun couldBlock(index: Index): Boolean {
        return board[index.row][index.col].isBlocking
    }
}