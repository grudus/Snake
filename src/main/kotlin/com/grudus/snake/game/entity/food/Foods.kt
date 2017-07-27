package com.grudus.snake.game.entity.food

import com.grudus.snake.game.Index
import com.grudus.snake.game.board.Board
import com.grudus.snake.game.entity.snake.Snake
import java.awt.Dimension
import java.awt.Graphics
import java.awt.image.ImageObserver
import java.util.*

class Foods {
    private val indexToFood = mutableMapOf<Index, Food>()
    private val random = Random()

    fun clean() = indexToFood.clear()
    fun newFood(index: Index) = indexToFood.put(index, randomFood())

    private fun  randomFood(): Food {
        val foodsSortedByProbability = Food.values().sortedBy{ it.probability }
        val probabilitySum = foodsSortedByProbability.sumBy { it.probability }
        val random = random.nextInt(probabilitySum)
        var tempSum = 0
        for (i in 0..foodsSortedByProbability.size) {
            tempSum += foodsSortedByProbability[i].probability
            if (tempSum > random)
                return foodsSortedByProbability[i]
        }
        throw CannotFindFoodException()
    }

    fun containsFood(index: Index) = indexToFood.containsKey(index)
    operator fun get(index: Index) = indexToFood[index]

    fun drawAll(g: Graphics, tileSize: Dimension, imageObserver: ImageObserver) =
            indexToFood.forEach { index, food -> food.draw(g, tileSize, index, imageObserver) }

    fun newFoodAtRandom(board: Board, snake: Snake, vararg disabledIndexes: Index): Food? {
        var index: Index?
        do {
            val col = random.nextInt(board.columns)
            val row = random.nextInt(board.rows)
            index = Index(row, col)

        } while (!board.isAccessible(row, col) || snake.isBody(index!!) || disabledIndexes.contains(index))

        return newFood(index)
    }

    fun interact(index: Index, snake: Snake) {
        get(index)?.interact(snake)
        indexToFood.remove(index)
    }
}