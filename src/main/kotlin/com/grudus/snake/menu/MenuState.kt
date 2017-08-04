package com.grudus.snake.menu

import com.grudus.snake.panels.selectable.Displayable

enum class MenuState(private val label: String) : Displayable {
    PLAY("PLAY"), SETTINGS("SETTINGS"), HIGH_SCORES("HIGH SCORES"), EXIT("EXIT");

    override fun display() = label

    companion object {
        fun asDisplayable() = values().map { it as Displayable }
    }
}