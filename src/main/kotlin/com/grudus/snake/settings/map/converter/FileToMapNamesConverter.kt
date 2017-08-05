package com.grudus.snake.settings.map.converter

import com.grudus.snake.meta.Config
import com.grudus.snake.panels.selectable.Displayable
import com.grudus.snake.panels.selectable.DisplayableText
import com.grudus.snake.utils.hasExtension
import java.io.File

class FileToMapNamesConverter {

    fun convert(directory: File): List<Displayable> {
        require(directory.isDirectory) {"File ${directory.absolutePath} is not a directory"}

        return directory.listFiles()
                .filter { it.hasExtension(Config.MAP_EXTENSION) }
                .map { it.nameWithoutExtension }
                .map { DisplayableText(it) }
    }

}