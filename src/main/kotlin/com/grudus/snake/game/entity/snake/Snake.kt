package com.grudus.snake.game.entity.snake

import com.grudus.snake.game.Position
import com.grudus.snake.game.board.Board
import com.grudus.snake.game.entity.Direction
import com.grudus.snake.game.entity.food.Foods
import java.awt.Dimension
import java.awt.Graphics

class Snake(val size: Dimension, private val startPosition: Position = Position(0, 0), val board: Board, val foods: Foods) {
    private val initialLength = 5
    var isDead = false
    var direction = Direction.RIGHT
        set(value) {
            if (direction.canChangeDirection(value))
                field = value
        }

    private val body: MutableList<SnakeTile> = createBody()

    private fun createBody(): MutableList<SnakeTile> {
        val body: MutableList<SnakeTile> = mutableListOf()
        body.add(SnakeHead(startPosition, size))

        return (1..initialLength).mapTo(body) { SnakeBody(startPosition - Position(it * size.width, 0), size) }
    }

    fun increaseBody() {
        body.add(SnakeBody(Position(body.last().position), size))
    }

    fun draw(g: Graphics) {
        body.forEach { it.draw(g) }
    }

    fun updatePosition() {
        if (isDead) return

        val headPosition = body[0].position
        val newHeadPosition = Position(headPosition.x + direction.dx * size.width,
                headPosition.y + direction.dy * size.height)

        if (board.couldBlock(newHeadPosition, size)) {
            isDead = true
            return
        }

        if (isBody(newHeadPosition)) {
            isDead = true
            return
        }

        if (foods.containsFood(newHeadPosition)) {
            foods.interact(newHeadPosition, this)
            foods.newFoodAtRandom(board, this, size)
        }

        for (i in body.size-1 downTo 1)
            body[i].changePosition(body[i - 1].position)
        body[0].changePosition(newHeadPosition)
    }

    fun isBody(position: Position) = body.any { body ->
        body.position == position
    }
}