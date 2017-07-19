package com.grudus.snake.game.entity.snake

import com.grudus.snake.game.Index
import com.grudus.snake.game.board.Board
import com.grudus.snake.game.entity.Direction
import com.grudus.snake.game.entity.food.Foods
import java.awt.Component
import java.awt.Dimension
import java.awt.Graphics

class Snake(val size: Dimension, private val startIndex: Index, val board: Board, val foods: Foods, private val component: Component) {
    private val initialLength = 5
    var isDead = false
    var direction = Direction.RIGHT
        set(value) {
            if (field.canChangeDirection(value))
                field = value
        }

    private val body: MutableList<SnakeTile> = createBody()

    private fun createBody(): MutableList<SnakeTile> {
        val body: MutableList<SnakeTile> = mutableListOf()
        body.add(SnakeHead(startIndex, Direction.RIGHT))

        return (1..initialLength).mapTo(body) {
            if (it == initialLength) SnakeTail(startIndex - Index(0, it), Direction.RIGHT)
            else SnakeBody(startIndex - Index(0, it), Direction.RIGHT)
        }
    }

    fun increaseBody() {
        body.add(body.size - 1, SnakeBody(Index(body.last().index), body.last().direction))
    }

    fun draw(g: Graphics) {
        body.forEach { it.draw(g, size, component) }
    }

    fun updatePosition() {
        if (isDead) return

        val headIndex = body[0].index
        val newHeadIndex = Index(headIndex.row + direction.dy, headIndex.col + direction.dx)

        if (board.couldBlock(newHeadIndex)) {
            isDead = true
            return
        }

        if (isBody(newHeadIndex)) {
            isDead = true
            return
        }

        if (foods.containsFood(newHeadIndex)) {
            foods.interact(newHeadIndex, this)
            foods.newFoodAtRandom(board, this)
        }

        body[0].direction = direction
        for (i in body.size - 1 downTo 1) {
            body[i].changePosition(body[i - 1].index, body[i - 1].direction)
            body[i-1].previousTileDirection = body[i].direction
        }
        body[0].changePosition(newHeadIndex, direction)
    }

    fun isBody(index: Index) = body.any { body ->
        body.index == index
    }
}