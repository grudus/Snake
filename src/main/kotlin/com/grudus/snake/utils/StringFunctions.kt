package com.grudus.snake.utils

fun String.truncate(length: Int) = if (this.length > length) "..." + this.substring(this.length - length) else this
fun isNullOrEmpty(string: String?) = string == null || string.isBlank()