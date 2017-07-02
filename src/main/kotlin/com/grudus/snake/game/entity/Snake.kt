package com.grudus.snake.game.entity

import com.grudus.snake.game.Position
import java.awt.Dimension
import java.awt.Graphics

class Snake(val size: Dimension, private val startPosition: Position = Position(0, 0)) {
    private val length = 5
    internal var direction = Direction.RIGHT
        set(value) {
            if (direction.canChangeDirection(value))
                field = value
        }
    private val body: List<SnakeTile> = createBody()

    private fun createBody(): List<SnakeTile> {
        val body: MutableList<SnakeTile> = mutableListOf()
        body.add(SnakeHead(startPosition, size))

        return (1..length).mapTo(body) { SnakeBody(startPosition - Position(it * size.width, 0), size) }
    }

    fun draw(g: Graphics) {
        body.forEach { it.draw(g) }
    }

    fun updatePosition() {
        val headPosition = body[0].position
        val newHeadPosition = Position(headPosition.x + direction.dx * size.width,
                headPosition.y + direction.dy * size.height)
        for (i in length downTo 1)
            body[i].changePosition(body[i - 1].position)
        body[0].changePosition(newHeadPosition)
    }
}