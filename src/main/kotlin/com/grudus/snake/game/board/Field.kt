package com.grudus.snake.game.board

import com.grudus.snake.game.Position
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics

enum class Field(val isBlocking: Boolean) {
    BLOCK(true) {
        override fun draw(g: Graphics, size: Dimension, position: Position) {
            g.color = Color.BLACK
            g.fillRect(position.x - size.width / 2, position.y - size.height / 2, size.width, size.height)
        }
    },

    EMPTY(false) {
        override fun draw(g: Graphics, size: Dimension, position: Position) {}
    }
    ;
    abstract fun draw(g: Graphics, size: Dimension, position: Position)
}