package com.grudus.snake.menu


import com.grudus.snake.Window
import com.grudus.snake.panels.selectable.Displayable
import com.grudus.snake.panels.selectable.SelectablePanel
import com.grudus.snake.utils.Colors


class MenuPanel(val window: Window, initialState: State) : SelectablePanel(State.asDisplayable(), initialState.ordinal) {

    init {
        background = Colors.MENU_BACKGROUND
    }

    override fun onStateSelected(selected: Int) = window.onStateChange(State.values()[selected])


    enum class State(private val label: String) : Displayable {
        PLAY("PLAY"), SETTINGS("SETTINGS"), HIGH_SCORES("HIGH SCORES"), EXIT("EXIT");

        override fun display() = label

        companion object {
            fun asDisplayable() = values().map { it as Displayable }
        }
    }
}
