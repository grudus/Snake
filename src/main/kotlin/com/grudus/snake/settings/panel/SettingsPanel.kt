package com.grudus.snake.settings.panel

import com.grudus.snake.event.EventBus
import com.grudus.snake.event.window.ChangePanelEvent
import com.grudus.snake.event.window.ShowMenuEvent
import com.grudus.snake.menu.MenuPanel
import com.grudus.snake.panels.selectable.Displayable
import com.grudus.snake.panels.selectable.SelectablePanel
import com.grudus.snake.settings.Settings
import com.grudus.snake.settings.map.BoardSettingsPanel
import com.grudus.snake.utils.Colors

class SettingsPanel(private val settings: Settings) : SelectablePanel(State.asDisplayable()) {

    init {
        background = Colors.SETTINGS_BACKGROUND
    }

    override fun onStateSelected(selected: Int) {
        when(State.values()[selected]) {
            State.MAP -> EventBus.publish(ChangePanelEvent(BoardSettingsPanel(settings)))
            State.CANCEL -> EventBus.publish(ShowMenuEvent(MenuPanel.State.SETTINGS))
        }
    }

    override fun onCancel() = EventBus.publish(ShowMenuEvent(MenuPanel.State.SETTINGS))

    private enum class State(private val label: String): Displayable {
        MAP("CHANGE MAP"), CANCEL("Cancel");

        override fun display() = label

        companion object {
            fun asDisplayable() = values().map { it as Displayable }
        }
    }
}
