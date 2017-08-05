package com.grudus.snake.panels.selectable

data class DisplayableText(val text: String): Displayable {
    override fun display() = text
}