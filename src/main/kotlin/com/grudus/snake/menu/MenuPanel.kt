package com.grudus.snake.menu


import com.grudus.snake.Window
import com.grudus.snake.panels.selectable.SelectablePanel
import com.grudus.snake.utils.Colors


class MenuPanel(val window: Window, initialState: MenuState) : SelectablePanel(MenuState.asDisplayable(), initialState.ordinal) {

    init {
        background = Colors.MENU_BACKGROUND
    }

    override fun onStateSelected(selected: Int) = window.onStateChange(MenuState.values()[selected])
}
