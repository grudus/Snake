package com.grudus.snake

import com.grudus.snake.game.GamePanel
import com.grudus.snake.game.Index
import com.grudus.snake.game.board.Board
import com.grudus.snake.game.board.generator.DefaultMapGenerator
import com.grudus.snake.game.board.generator.SnMapGenerator
import com.grudus.snake.menu.MenuPanel
import com.grudus.snake.menu.MenuState
import com.grudus.snake.settings.CurrentSettings
import com.grudus.snake.settings.SettingsPanel
import java.awt.Dimension
import java.awt.Font
import java.awt.GraphicsEnvironment
import java.awt.Toolkit
import java.awt.event.WindowEvent
import java.io.File
import javax.swing.JFrame
import javax.swing.JFrame.EXIT_ON_CLOSE
import javax.swing.UIManager

class Window(title: String, val width: Int = 800, val height: Int = 600) {
    val settings = CurrentSettings(this.javaClass.classLoader.getResource("application.properties").path)
    private val frame = JFrame(title)

    init {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
        val roboto = Font.createFont(Font.TRUETYPE_FONT, javaClass.classLoader.getResourceAsStream("font/Roboto-Regular.ttf"));
        GraphicsEnvironment.getLocalGraphicsEnvironment()
                .registerFont(roboto)
        frame.defaultCloseOperation = EXIT_ON_CLOSE
        frame.size = Dimension(width, height)
        frame.minimumSize = Dimension(width / 2, height / 2)
        centerOnTheScreen()
        frame.contentPane = MenuPanel(this)
    }

    private fun centerOnTheScreen() {
        val screenSize = Toolkit.getDefaultToolkit().screenSize
        frame.setLocation(screenSize.width / 2 - width / 2, screenSize.height / 2 - height / 2)
    }


    fun show() {
        frame.isVisible = true
    }

    fun onStateChange(menuState: MenuState) {
        println(menuState)
        when (menuState) {
            MenuState.EXIT -> closeWindow()
            MenuState.PLAY -> changeView(GamePanel(this))
            MenuState.SETTINGS -> openSettings()
        }
    }

    private fun openSettings() {
        frame.contentPane = SettingsPanel(this)
        frame.validate()
        frame.repaint()
    }

    private fun changeView(panel: GamePanel) {
        frame.contentPane = panel
        frame.validate()
        frame.repaint()
        panel.start()
    }

    fun showMenuPanel() {
        val panel = MenuPanel(this)
        frame.contentPane = panel
        frame.validate()
        frame.repaint()
        panel.isFocusable = true
        panel.requestFocus()

    }

    private fun closeWindow() = frame.dispatchEvent(WindowEvent(frame, WindowEvent.WINDOW_CLOSING))

    // TODO 24.07.2017 get from settings
    fun getBoard(): Board =
            if (settings.boardFilePath == null) DefaultMapGenerator().generate(settings.boardSize ?: Index(8, 8))
            else SnMapGenerator().generate(File(settings.boardFilePath))

}