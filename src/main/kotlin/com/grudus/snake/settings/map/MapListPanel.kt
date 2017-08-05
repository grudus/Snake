package com.grudus.snake.settings.map

import com.grudus.snake.game.board.Board
import com.grudus.snake.game.board.generator.SnMapGenerator
import com.grudus.snake.meta.Config
import com.grudus.snake.panels.selectable.SelectablePanel
import com.grudus.snake.settings.map.converter.FileToMapNamesConverter
import com.grudus.snake.utils.Colors
import com.grudus.snake.utils.hasExtension
import com.grudus.snake.utils.putIfAbsent
import java.io.File

class MapListPanel(private val mapDirectory: File) : SelectablePanel(FileToMapNamesConverter().convert(mapDirectory)) {

    var onMapChanged: (Board) -> Unit = {}
    var onMapSelected: (File) -> Unit = {}
    var onCancel: () -> Unit = {}
    private val cache = mutableMapOf<String, Board>()
    private val generator = SnMapGenerator()

    init {
        background = Colors.PANEL_BACKGROUND
    }

    override fun onInit() {
        super.onInit()
    }

    override fun onStateSelected(selected: Int) = onMapSelected(findSelectedFile())

    override fun onChange(selected: Int) {
        val mapName = currentSelected().display()

        val map = cache.putIfAbsent(mapName) { generate() }
        onMapChanged(map)
    }

    override fun onCancel() = this.onCancel.invoke()

    fun initialSelect() = generate()

    private fun findSelectedFile(): File {
        val mapName = currentSelected().display()
        return mapDirectory.listFiles().
                filter { it.hasExtension(Config.MAP_EXTENSION) }
                .find { it.nameWithoutExtension == mapName } ?: throw IllegalStateException("Cannot find map $mapName")

    }

    private fun generate() = generator.generate(findSelectedFile())
}