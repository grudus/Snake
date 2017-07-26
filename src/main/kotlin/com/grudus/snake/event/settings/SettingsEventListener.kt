package com.grudus.snake.event.settings

import com.grudus.snake.event.EventBus
import com.grudus.snake.event.EventListener
import com.grudus.snake.settings.SettingsWriter

class SettingsEventListener(path: String): EventListener() {
    private val settingsWriter =  SettingsWriter(path)

    override fun startListening() {
        EventBus.listen(SaveSettingsEvent::class.java).subscribe {
            settingsWriter.write(it.settings)
            log.debug("Successfully write settings")
        }
    }
}