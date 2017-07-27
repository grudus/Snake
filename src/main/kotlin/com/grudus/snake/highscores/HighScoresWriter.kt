package com.grudus.snake.highscores

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.slf4j.LoggerFactory
import java.io.File

class HighScoresWriter(path: String) {
    private val mapper: ObjectMapper = jacksonObjectMapper()
    private val file = File(this.javaClass.classLoader.getResource(path).path)
    private val log = LoggerFactory.getLogger(javaClass)

    fun write(highScores: HighScores) {
        log.info("Write high scores to file ${file.absolutePath}")
        mapper.writeValue(file, highScores.scores)
    }
}