package com.grudus.snake.settings

import com.grudus.snake.Window
import com.grudus.snake.event.EventBus
import com.grudus.snake.event.settings.SaveSettingsEvent
import com.grudus.snake.utils.component.SubmitCancelButtons
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.Insets
import javax.swing.JPanel

class SettingsPanel(val window: Window) : JPanel() {
    private val grid = GridBagConstraints()
    private val boardSettings = BoardSettingsPanel(window.settings)
    private val buttons = SubmitCancelButtons({ if (save()) goToMenu() }, { goToMenu() }, "Save")

    init {
        layout = GridBagLayout()

        initView()
    }

    private fun goToMenu() = window.showMenuPanel()

    private fun save(): Boolean {
        if (!boardSettings.isChangePossible()) return false

        boardSettings.updateSettings()
        EventBus.publish(SaveSettingsEvent(window.settings))
        return true
    }

    private fun initView() {
        grid.gridx = 0
        grid.weightx = 0.5
        grid.weighty = 0.5
        grid.gridy = 0
        grid.insets = Insets(16, 16, 16, 16)
        grid.anchor = GridBagConstraints.FIRST_LINE_START
        add(boardSettings, grid)

        grid.gridx = 3
        grid.gridy = 3
        grid.anchor = GridBagConstraints.LAST_LINE_END
        add(buttons, grid)
    }
}