package com.grudus.snake.game
import com.grudus.snake.game.menu.MenuPanel
import java.awt.Dimension
import java.awt.Font
import java.awt.GraphicsEnvironment
import java.awt.Toolkit
import javax.swing.JFrame
import javax.swing.JFrame.EXIT_ON_CLOSE

class Window(title: String, val width: Int = 800, val height: Int = 600) {
    private val context = Context()
    private val MENU_PANEL = MenuPanel(context)
    private val GAME_PANEL = GamePanel(context)
    private val frame = JFrame(title)
    private var currentPanel = MENU_PANEL

    init {
        val roboto = Font.createFont(Font.TRUETYPE_FONT, javaClass.classLoader.getResourceAsStream("font/Roboto-Regular.ttf"));
        GraphicsEnvironment.getLocalGraphicsEnvironment()
                .registerFont(roboto)
        frame.defaultCloseOperation = EXIT_ON_CLOSE
        frame.size = Dimension(width, height)
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
}