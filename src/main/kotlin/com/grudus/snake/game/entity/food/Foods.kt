package com.grudus.snake.game.entity.food

import com.grudus.snake.game.Position
import com.grudus.snake.game.board.Board
import com.grudus.snake.game.entity.snake.Snake
import java.awt.Dimension
import java.awt.Graphics
import java.util.*

class Foods {
    private val positionToFood = mutableMapOf<Position, Food>()
    private val random = Random()
    private val possibleFoods = listOf(NormalFood(), NormalFood(), BigIncreaseFood())

    fun clean() = positionToFood.clear()
    fun newFood(position: Position) = positionToFood.put(position, possibleFoods[random.nextInt(possibleFoods.size)])
    fun containsFood(position: Position) = positionToFood.containsKey(position)
    operator fun get(position: Position) = positionToFood[position]
    fun drawAll(g: Graphics, tileSize: Dimension) = positionToFood.forEach { position, food -> food.draw(g, tileSize, position) }
    fun newFoodAtRandom(board: Board, snake: Snake, tileSize: Dimension) : Food? {
        var position: Position?
        do {
            val col = random.nextInt(board.columns)
            val row = random.nextInt(board.rows)
            position = Position(col * tileSize.width, row * tileSize.height)
        } while (!board.isAccessible(row, col) && !snake.isBody(position!!))

        return newFood(position!!)
    }

    fun  interact(newHeadPosition: Position, snake: Snake) {
        get(newHeadPosition)?.interact(snake)
        positionToFood.remove(newHeadPosition)
    }
}