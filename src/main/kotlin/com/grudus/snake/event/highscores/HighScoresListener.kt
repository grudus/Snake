package com.grudus.snake.event.highscores

import com.grudus.snake.event.EventBus
import com.grudus.snake.event.EventListener
import com.grudus.snake.highscores.HighScores
import com.grudus.snake.highscores.HighScoresWriter

class HighScoresListener(private val highScores: HighScores, path: String): EventListener() {
    private val highScoresWriter =  HighScoresWriter(path)

    override fun startListening() {
        EventBus.listen(NewHighScoreEvent::class.java).subscribe {
            val scores = highScores.newHighScores(it.score)
            highScoresWriter.write(scores)
            log.debug("Successfully write high scores with new score: ${it.score}")
        }
    }
}