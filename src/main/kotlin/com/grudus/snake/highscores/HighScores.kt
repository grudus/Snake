package com.grudus.snake.highscores

data class HighScores(var scores: List<Score>) {

    fun isScoreBeaten(points: Int) = points >= scores.minBy { it.points }!!.points

    fun newHighScores(score: Score): HighScores {
        val min = scores.minBy { it.points }!!
        val scores = scores.map { if (it === min) score else it }
        return HighScores(scores)
    }
}