package com.grudus.snake

import com.grudus.snake.event.settings.SettingsEventListener
import com.grudus.snake.game.GamePanel
import com.grudus.snake.game.Index
import com.grudus.snake.game.board.Board
import com.grudus.snake.game.board.generator.DefaultMapGenerator
import com.grudus.snake.game.board.generator.SnMapGenerator
import com.grudus.snake.highscores.HighScoresPanel
import com.grudus.snake.menu.MenuPanel
import com.grudus.snake.menu.MenuState
import com.grudus.snake.settings.SettingsPanel
import com.grudus.snake.settings.SettingsReader
import java.awt.Dimension
import java.awt.Font
import java.awt.GraphicsEnvironment
import java.awt.Toolkit
import java.awt.event.WindowEvent
import java.io.File
import javax.swing.JFrame
import javax.swing.JFrame.EXIT_ON_CLOSE
import javax.swing.UIManager

class Window(title: String, val width: Int = 800, val height: Int = 680, propertiesPath: String = "settings.json") {
    val settings = SettingsReader(propertiesPath).read()
    private val frame = JFrame(title)

    init {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
        SettingsEventListener(propertiesPath).startListening()
        registerFont()
        initFrameSize()
        frame.defaultCloseOperation = EXIT_ON_CLOSE
        frame.contentPane = MenuPanel(this)
    }

    fun show() {
        frame.isVisible = true
    }

    fun getBoard(): Board =
            if (settings.boardFilePath == null) DefaultMapGenerator().generate(settings.boardSize ?: Index(8, 8))
            else SnMapGenerator().generate(File(settings.boardFilePath))

    fun onStateChange(menuState: MenuState) {
        when (menuState) {
            MenuState.EXIT -> frame.dispatchEvent(WindowEvent(frame, WindowEvent.WINDOW_CLOSING))
            MenuState.PLAY -> startGame()
            MenuState.SETTINGS -> frame.contentPane = SettingsPanel(this)
            MenuState.HIGH_SCORES -> frame.contentPane = HighScoresPanel()
        }
        frame.validate()
        frame.repaint()
    }

    fun showMenuPanel() {
        val panel = MenuPanel(this)
        frame.contentPane = panel
        panel.isFocusable = true
        panel.requestFocus()
        frame.validate()
        frame.repaint()
    }


    private fun startGame() {
        val panel = GamePanel(getBoard())
        frame.contentPane = panel
        panel.onInit()
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
