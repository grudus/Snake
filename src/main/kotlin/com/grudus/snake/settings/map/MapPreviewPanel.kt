package com.grudus.snake.settings.map

import com.grudus.snake.LifeCyclePanel
import com.grudus.snake.game.board.Board
import com.grudus.snake.utils.Colors
import java.awt.Dimension
import java.awt.Graphics
import java.awt.event.ComponentEvent
import java.awt.event.ComponentListener

class MapPreviewPanel(initialMap: Board): LifeCyclePanel(), ComponentListener {

    private var map = initialMap
    private var tileDimension = Dimension(10, 10)

    init {
        background = Colors.PANEL_BACKGROUND
        addComponentListener(this)
    }

    fun changeMap(board: Board) {
        map = board
        repaint()
    }

    override fun paintComponent(g: Graphics?) {
        super.paintComponent(g!!)

        map.draw(g, tileDimension, this)
    }

    override fun componentResized(e: ComponentEvent?) {
        this.tileDimension.width = width / map.columns
        this.tileDimension.height = height / map.rows
        repaint()
    }

    override fun componentHidden(e: ComponentEvent?) {}
    override fun componentShown(e: ComponentEvent?) {}
    override fun componentMoved(e: ComponentEvent?) {}
}
