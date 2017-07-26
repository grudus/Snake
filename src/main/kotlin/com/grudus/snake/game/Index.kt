package com.grudus.snake.game

data class Index(val row: Int, val column: Int) {
    init {
        require(row >= 0 && column >= 0) {"Index cannot have negative values"}
    }
    constructor(position: Index) : this(position.row, position.column)

    operator fun plus(index: Index) = Index(row + index.row, column + index.column)
    operator fun minus(index: Index) = Index(row - index.row, column - index.column)
}