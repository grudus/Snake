package com.grudus.snake.settings

import com.grudus.snake.game.Index
import com.grudus.snake.utils.nullOnEmpty
import java.io.*
import java.util.*

class CurrentSettings(private val path: String) {
    private val properties = Properties()
    private val propertiesFile = File(path)

    var boardSize: Index? = null
    var boardFilePath: String? = null

    init {
        BufferedInputStream(FileInputStream(propertiesFile)).use {
            properties.load(it)
        }


        whenNonNulls(properties.getProperty("board.size.columns").nullOnEmpty(), properties.getProperty("board.size.rows").nullOnEmpty()) {
            boardSize = Index(properties.getProperty("board.size.rows").toInt(), properties.getProperty("board.size.columns").toInt())
        }
        boardFilePath = properties.getProperty("board.path").nullOnEmpty()
    }


    fun save() {
        properties.put("board.size.columns", boardSize?.col?.toString() ?: "")
        properties.put("board.size.rows", boardSize?.row?.toString() ?: "")
        properties.put("board.path", boardFilePath ?: "")

        BufferedWriter(FileWriter(propertiesFile)).use {
            properties.store(it, "Grudus Snake Props")
        }
    }

    private fun whenNonNulls(vararg args: String?, function: () -> Unit) {
        if (args.all { it != null })
            function()
    }
}