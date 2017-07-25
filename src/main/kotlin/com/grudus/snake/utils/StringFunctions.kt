package com.grudus.snake.utils

fun String.truncate(length: Int) = if (this.length > length) "..." + this.substring(this.length - length) else this
fun String.nullOnEmpty() = if (this.all { Character.isWhitespace(it) }) null else this