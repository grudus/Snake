package com.grudus.snake.highscores

data class HighScores(var scores: MutableList<Score>) {

    fun isScoreBeaten(points: Int) = points >= scores.minBy { it.points }!!.points

    fun newHighScores(score: Score): HighScores {
        val min = scores.minBy { it.points }!!
        scores[scores.indexOf(min)] = score
        return HighScores(scores)
    }
}