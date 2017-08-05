package com.grudus.snake.settings.map

import com.grudus.snake.LifeCyclePanel
import com.grudus.snake.event.EventBus
import com.grudus.snake.event.settings.SaveSettingsEvent
import com.grudus.snake.event.window.ChangePanelEvent
import com.grudus.snake.meta.Config
import com.grudus.snake.settings.Settings
import com.grudus.snake.settings.panel.SettingsPanel
import com.grudus.snake.utils.Colors
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.Insets
import java.io.File

class BoardSettingsPanel(val settings: Settings) : LifeCyclePanel() {

    private val mapListPanel = MapListPanel(File(Config.MAPS_FOLDER))
    private val mapPreviewPanel = MapPreviewPanel(mapListPanel.initialSelect())

    init {
        background = Colors.PANEL_BACKGROUND
        layout = GridBagLayout()

        with(mapListPanel) {
            onMapChanged = { mapPreviewPanel.changeMap(it) }
            onMapSelected = {
                settings.boardFilePath = it.absolutePath
                EventBus.publish(SaveSettingsEvent(settings))
                onCancel()
            }
            onCancel = { EventBus.publish(ChangePanelEvent(SettingsPanel(settings))) }
        }
    }

    override fun onInit() {
        initViews()
        mapListPanel.onInit()
    }

    private fun initViews() {
        val grid = GridBagConstraints()
        grid.fill = GridBagConstraints.BOTH
        grid.gridx = 0
        grid.gridy = 0
        grid.weightx = 0.75
        grid.weighty = 1.0

        add(mapListPanel, grid)

        grid.gridx = 1
        grid.weightx = 1.0
        grid.insets = Insets(24, 40, 120, 40)
        add(mapPreviewPanel, grid)
    }
}
