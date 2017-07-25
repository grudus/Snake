package com.grudus.snake.utils

import java.io.File

fun File.hasExtension(extenstion: String) = this.absolutePath.substring(this.absolutePath.lastIndexOf(".") + 1).equals(extenstion, true)