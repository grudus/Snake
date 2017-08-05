package com.grudus.snake

import com.grudus.snake.event.highscores.HighScoresListener
import com.grudus.snake.event.settings.SettingsEventListener
import com.grudus.snake.event.window.WindowEventListener
import com.grudus.snake.game.GamePanel
import com.grudus.snake.game.Index
import com.grudus.snake.game.board.Board
import com.grudus.snake.game.board.generator.DefaultMapGenerator
import com.grudus.snake.game.board.generator.SnMapGenerator
import com.grudus.snake.highscores.HighScores
import com.grudus.snake.highscores.HighScoresPanel
import com.grudus.snake.highscores.HighScoresReader
import com.grudus.snake.menu.MenuPanel
import com.grudus.snake.menu.MenuPanel.State.*
import com.grudus.snake.settings.SettingsReader
import com.grudus.snake.settings.panel.SettingsPanel
import com.grudus.snake.utils.StringUtils
import java.awt.Dimension
import java.awt.Font
import java.awt.GraphicsEnvironment
import java.awt.Toolkit
import java.awt.event.WindowEvent
import java.io.File
import javax.swing.JFrame
import javax.swing.JFrame.EXIT_ON_CLOSE
import javax.swing.UIManager

class Window(title: String, val width: Int = 800, val height: Int = 680, propertiesPath: String = "config/settings.json", highScoresPath: String = "config/high_scores.json") {
    val settings = SettingsReader(propertiesPath).read()
    val highScores = HighScores(HighScoresReader(highScoresPath).read())
    private val frame = JFrame(title)

    init {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
        SettingsEventListener(propertiesPath).startListening()
        HighScoresListener(highScores, highScoresPath).startListening()
        WindowEventListener(this).startListening()
        registerFont()
        initFrameSize()
        frame.defaultCloseOperation = EXIT_ON_CLOSE
        showMenuPanel()
    }

    fun show() {
        frame.isVisible = true
    }

    fun getBoard(): Board =
            if (StringUtils.isBlank(settings.boardFilePath)) DefaultMapGenerator().generate(settings.boardSize ?: Index(8, 8))
            else SnMapGenerator().generate(File(settings.boardFilePath))


    fun changePanel(panel: LifeCyclePanel) {
        frame.contentPane = panel
        panel.onInit()
        frame.validate()
        frame.repaint()
    }
    
    fun onStateChange(menuState: MenuPanel.State) {
        when (menuState) {
            EXIT -> frame.dispatchEvent(WindowEvent(frame, WindowEvent.WINDOW_CLOSING))
            PLAY -> changePanel(GamePanel(getBoard(), highScores))
            SETTINGS -> changePanel(SettingsPanel(settings))
            HIGH_SCORES -> changePanel(HighScoresPanel(highScores))
        }
    }


    fun showMenuPanel(currentState: MenuPanel.State = PLAY) {
        changePanel(MenuPanel(this, currentState))
    }


    private fun registerFont() {
        val roboto = Font.createFont(Font.TRUETYPE_FONT, javaClass.classLoader.getResourceAsStream("font/Roboto-Regular.ttf"))
        GraphicsEnvironment.getLocalGraphicsEnvironment()
                .registerFont(roboto)
    }

    private fun initFrameSize() {
        frame.size = Dimension(width, height)
        frame.minimumSize = Dimension(width / 2, height / 2)
        centerOnTheScreen()
    }

    private fun centerOnTheScreen() {
        val screenSize = Toolkit.getDefaultToolkit().screenSize
        frame.setLocation(screenSize.width / 2 - width / 2, screenSize.height / 2 - height / 2)
    }
}
