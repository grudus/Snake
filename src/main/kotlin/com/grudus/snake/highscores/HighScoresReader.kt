package com.grudus.snake.highscores

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.slf4j.LoggerFactory
import java.io.File

class HighScoresReader(path: String) {
    private val mapper: ObjectMapper = jacksonObjectMapper()
    private val file = File(this.javaClass.classLoader.getResource(path).path)
    private val log = LoggerFactory.getLogger(javaClass)

    fun read(): MutableList<Score> {
        log.info("Reading high scores from file ${file.absolutePath}")
        return mapper.readValue<MutableList<Score>>(file)
    }
}