package com.grudus.snake.utils

import java.io.File

fun File.hasExtension(extension: String) = this.extension.equals(extension, true)