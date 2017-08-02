package com.grudus.snake.utils

object StringUtils {
    fun isBlank(string: String?) = string == null || string.all { it.isWhitespace() }
}