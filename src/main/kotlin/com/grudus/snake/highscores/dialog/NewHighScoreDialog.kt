package com.grudus.snake.highscores.dialog

import com.grudus.snake.utils.component.CustomDialog
import java.awt.Dimension
import java.awt.event.KeyEvent

class NewHighScoreDialog(score: Int) : CustomDialog("New high score!", Dimension(600, 250)) {

    init {
        contentPane = NewHighScorePanel(score, this)
    }

    override fun keyPressed(e: KeyEvent?) {
        (contentPane as NewHighScorePanel).keyPressed(e!!)
    }
}