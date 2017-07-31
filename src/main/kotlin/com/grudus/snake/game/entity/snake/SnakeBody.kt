package com.grudus.snake.game.entity.snake

import com.grudus.snake.game.Index
import com.grudus.snake.game.entity.Direction
import java.awt.Component
import java.awt.Dimension
import java.awt.Graphics

class SnakeBody(private val initialLength: Int, private val startIndex: Index) {
    private val body = createBody()
    val head by lazy { body[0] as SnakeHeadTile }
    var size = 0
        get() {
            return body.size
        }

    operator fun get(index: Int) = body[index]

    fun draw(g: Graphics, size: Dimension, component: Component) = body.forEach { it.draw(g, size, component) }

    fun contains(index: Index): Boolean = body.any { tile ->
        tile.index == index
    }

    fun changePositionToPrevious(index: Int) {
        body[index].changePosition(body[index - 1].index, body[index - 1].direction)
        updatePreviousTileDirection(index)
    }

    fun removeLast() = body.removeAt(body.size - 2)

    fun duplicateLast() = add(SnakeBodyTile(Index(body.last().index), body.last().direction))

    private fun add(tile: SnakeTile) = body.add(body.size - 1, tile)

    private fun updatePreviousTileDirection(index: Int) {
        body[index - 1].previousTileDirection = body[index].direction
    }

    private fun createBody(): MutableList<SnakeTile> {
        val body: MutableList<SnakeTile> = mutableListOf()
        body.add(SnakeHeadTile(startIndex, Direction.RIGHT))

        return (1..initialLength).mapTo(body) {
            val index = startIndex - Index(0, it)
            if (it == initialLength) SnakeTailTile(index, Direction.RIGHT)
            else SnakeBodyTile(index, Direction.RIGHT)
        }
    }

}