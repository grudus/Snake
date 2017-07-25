package com.grudus.snake.game.entity.snake

import com.grudus.snake.event.*
import com.grudus.snake.game.Index
import com.grudus.snake.game.Speed
import com.grudus.snake.game.board.Board
import com.grudus.snake.game.entity.Direction
import com.grudus.snake.game.entity.food.Foods
import java.awt.Component
import java.awt.Dimension
import java.awt.Graphics

class Snake(private val startIndex: Index, startSpeed: Speed, private val initialLength: Int) {
    var currentSpeed = startSpeed
    var direction = Direction.RIGHT
        set(value) {
            if (field.canChangeDirection(value))
                field = value
        }

    private val body: MutableList<SnakeTile> = createBody()


    fun updatePosition(board: Board, foods: Foods) {
        val headIndex = body[0].index
        val newHeadIndex = Index(headIndex.row + direction.dy, headIndex.col + direction.dx)

        if (board.couldBlock(newHeadIndex) || isBody(newHeadIndex)) {
            EventBus.publish(GameEndEvent())
            return
        }

        if (foods.containsFood(newHeadIndex)) {
            EventBus.publish(FoodEatenEvent(foods[newHeadIndex]!!))
            foods.interact(newHeadIndex, this)
            foods.newFoodAtRandom(board, this)
            EventBus.publish(UpdateSnakeSizeEvent(bodyLength()))
        }

        body[0].direction = direction
        for (i in body.size - 1 downTo 1) {
            body[i].changePosition(body[i - 1].index, body[i - 1].direction)
            body[i - 1].previousTileDirection = body[i].direction
        }
        body[0].changePosition(newHeadIndex, direction)
    }


    fun draw(g: Graphics, size: Dimension, component: Component) = body.forEach { it.draw(g, size, component) }

    fun increaseBody() = body.add(body.size - 1, SnakeBody(Index(body.last().index), body.last().direction))

    fun decreaseBody() {
        if (bodyLength() > 3)
            body.removeAt(body.size - 2)
    }

    fun increaseSpeed() {
        currentSpeed = currentSpeed.nextSpeed()
        EventBus.publish(ChangeSpeedEvent(currentSpeed))
    }
    fun decreaseSpeed() {
        currentSpeed = currentSpeed.previousSpeed()
        EventBus.publish(ChangeSpeedEvent(currentSpeed))
    }

    fun isBody(index: Index) = body.any { body ->
        body.index == index
    }

    fun bodyLength() = body.size

    private fun createBody(): MutableList<SnakeTile> {
        val body: MutableList<SnakeTile> = mutableListOf()
        body.add(SnakeHead(startIndex, Direction.RIGHT))

        return (1..initialLength).mapTo(body) {
            if (it == initialLength) SnakeTail(startIndex - Index(0, it), Direction.RIGHT)
            else SnakeBody(startIndex - Index(0, it), Direction.RIGHT)
        }
    }
}