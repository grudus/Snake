package com.grudus.snake.game.entity.snake

import com.grudus.snake.event.EventBus
import com.grudus.snake.event.game.ChangeSpeedEvent
import com.grudus.snake.event.game.FoodEatenEvent
import com.grudus.snake.event.game.GameEndEvent
import com.grudus.snake.event.game.UpdateSnakeSizeEvent
import com.grudus.snake.game.Index
import com.grudus.snake.game.Speed
import com.grudus.snake.game.board.Board
import com.grudus.snake.game.entity.Direction
import com.grudus.snake.game.entity.food.Foods
import java.awt.Component
import java.awt.Dimension
import java.awt.Graphics

class Snake(startIndex: Index, startSpeed: Speed, initialLength: Int) {
    var currentSpeed = startSpeed
    var direction = Direction.RIGHT
        set(value) {
            if (field.canChangeDirection(value))
                field = value
        }

    private val body = SnakeBody(initialLength, startIndex)

    fun updatePosition(board: Board, foods: Foods) {
        body.head.direction = direction
        val newHeadIndex = calculateNewHeadIndex(board)

        if (board.couldBlock(newHeadIndex) || isBody(newHeadIndex)) {
            EventBus.publish(GameEndEvent())
            return
        }

        if (foods.containsFood(newHeadIndex)) {
            onFoodEaten(foods, board, newHeadIndex)
        }

        for (i in body.size - 1 downTo 1)
            body.changePositionToPrevious(i)

        body.head.changePosition(newHeadIndex, direction)
    }

    fun draw(g: Graphics, size: Dimension, component: Component) = body.draw(g, size, component)

    fun increaseBody() {
        body.duplicateLast()
        EventBus.publish(UpdateSnakeSizeEvent(body.size))
    }

    fun decreaseBody() {
        if (body.size > 3) {
            body.removeLast()
            EventBus.publish(UpdateSnakeSizeEvent(body.size))
        }
    }

    fun increaseSpeed() {
        currentSpeed = currentSpeed.nextSpeed()
        EventBus.publish(ChangeSpeedEvent(currentSpeed))
    }

    fun decreaseSpeed() {
        currentSpeed = currentSpeed.previousSpeed()
        EventBus.publish(ChangeSpeedEvent(currentSpeed))
    }

    fun isBody(index: Index) = body.contains(index)

    private fun onFoodEaten(foods: Foods, board: Board, newHeadIndex: Index) {
        EventBus.publish(FoodEatenEvent(foods[newHeadIndex]!!))
        foods.interact(newHeadIndex, this)
        foods.newFoodAtRandom(board, this, newHeadIndex)
    }

    private fun calculateNewHeadIndex(board: Board): Index {
        val current = body.head.index
        val lastRow = board.rows - 1
        val lastColumn = board.columns - 1
        val newRow = if (leavesBoardFromTopSide(current)) lastRow else (current.row + direction.dy) % board.rows
        val newColumn = if (leavesBoardFromLeftSide(current)) lastColumn else (current.column + direction.dx) % board.columns
        return Index(newRow, newColumn)
    }

    private fun leavesBoardFromLeftSide(headIndex: Index) = headIndex.column + direction.dx < 0
    private fun leavesBoardFromTopSide(headIndex: Index) = headIndex.row + direction.dy < 0
}
