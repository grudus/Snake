package com.grudus.snake.event.window

import com.grudus.snake.Window
import com.grudus.snake.event.EventBus
import com.grudus.snake.event.EventListener

class WindowEventListener(val window: Window): EventListener() {

    override fun startListening() {
        EventBus.listen(ShowMenuEvent::class.java).subscribe {
            log.debug("Going back the to menu from " + it.state)
            window.showMenuPanel(it.state)
        }
    }
}