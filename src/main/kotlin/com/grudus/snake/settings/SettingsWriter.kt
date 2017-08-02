package com.grudus.snake.settings

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.slf4j.LoggerFactory
import java.io.File

class SettingsWriter(path: String) {
    private val mapper: ObjectMapper = jacksonObjectMapper()
    private val file = File(path)
    private val log = LoggerFactory.getLogger(javaClass)

    fun write(settings: Settings) {
        log.info("Write settings to file ${file.absolutePath}")
        mapper.writeValue(file, settings)
    }
}