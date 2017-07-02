package com.grudus.snake.game.entity

import com.grudus.snake.game.Position
import com.grudus.snake.game.board.Board
import java.awt.Dimension
import java.awt.Graphics

class Snake(val size: Dimension, private val startPosition: Position = Position(0, 0), val board: Board) {
    private val initialLength = 5
    var isDead = false
    var direction = Direction.RIGHT
        set(value) {
            if (direction.canChangeDirection(value))
                field = value
        }

    private val body: List<SnakeTile> = createBody()

    private fun createBody(): List<SnakeTile> {
        val body: MutableList<SnakeTile> = mutableListOf()
        body.add(SnakeHead(startPosition, size))

        return (1..initialLength).mapTo(body) { SnakeBody(startPosition - Position(it * size.width, 0), size) }
    }

    fun draw(g: Graphics) {
        body.forEach { it.draw(g) }
    }

    fun updatePosition() {
        val headPosition = body[0].position
        val newHeadPosition = Position(headPosition.x + direction.dx * size.width,
                headPosition.y + direction.dy * size.height)

        if (board.couldBlock(newHeadPosition, size)) {
            isDead = true
            return
        }

        for (i in initialLength downTo 1)
            body[i].changePosition(body[i - 1].position)
        body[0].changePosition(newHeadPosition)
    }
}