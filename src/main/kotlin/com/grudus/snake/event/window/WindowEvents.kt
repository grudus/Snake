package com.grudus.snake.event.window

import com.grudus.snake.LifeCyclePanel
import com.grudus.snake.menu.MenuPanel

class ShowMenuEvent(val state: MenuPanel.State)
class ChangePanelEvent(val panel: LifeCyclePanel)