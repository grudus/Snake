package com.grudus.snake.settings

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.slf4j.LoggerFactory
import java.io.File

class SettingsReader(path: String) {
    private val mapper: ObjectMapper = jacksonObjectMapper()
    private val file = File(this.javaClass.classLoader.getResource(path).path)
    private val log = LoggerFactory.getLogger(javaClass)

    fun read(): Settings  {
        log.info("Reading settings from file ${file.absolutePath}")
        return mapper.readValue<Settings>(file)
    }
}