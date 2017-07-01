package com.grudus.snake

import com.grudus.snake.game.GamePanel
import com.grudus.snake.menu.MenuPanel
import com.grudus.snake.menu.MenuState
import java.awt.Dimension
import java.awt.Font
import java.awt.GraphicsEnvironment
import java.awt.Toolkit
import java.awt.event.WindowEvent
import javax.swing.JFrame
import javax.swing.JFrame.EXIT_ON_CLOSE

class Window(title: String, val width: Int = 800, val height: Int = 600) {
    private val MENU_PANEL = MenuPanel(this)
    private val GAME_PANEL = GamePanel(this)
    private val frame = JFrame(title)
    private var currentPanel = MENU_PANEL

    init {
        val roboto = Font.createFont(Font.TRUETYPE_FONT, javaClass.classLoader.getResourceAsStream("font/Roboto-Regular.ttf"));
        GraphicsEnvironment.getLocalGraphicsEnvironment()
                .registerFont(roboto)
        frame.defaultCloseOperation = EXIT_ON_CLOSE
        frame.size = Dimension(width, height)
        frame.minimumSize = Dimension(width / 2, height / 2)
        centerOnTheScreen()
        frame.contentPane = currentPanel
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
            MenuState.PLAY -> changeView(GAME_PANEL)
        }
    }

    private fun changeView(panel: GamePanel) {
        frame.contentPane = panel
        frame.validate()
        frame.repaint()
        panel.start()
    }

    private fun closeWindow() = frame.dispatchEvent(WindowEvent(frame, WindowEvent.WINDOW_CLOSING))
}