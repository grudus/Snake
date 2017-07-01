package com.grudus.snake.game.entity

import com.grudus.snake.game.Position
import java.awt.Dimension
import java.awt.Graphics

class Snake(val size: Dimension, private val startPosition: Position = Position(0, 0)) {
    private val length = 5
    private val direction = Direction.RIGHT
    private val body: List<SnakeTile> = createBody()

    private fun  createBody(): List<SnakeTile> {
        val body: MutableList<SnakeTile> = mutableListOf()
        body.add(SnakeHead(startPosition, size))
        (1..length).mapTo(body) { SnakeBody(startPosition - Position(it * size.width, 0), size) }
        return body
    }

    fun draw(g: Graphics) {
        body.forEach{it.draw(g)}
    }

    fun updatePosition() {
        body.forEach{it.changeX(5)}
    }
}