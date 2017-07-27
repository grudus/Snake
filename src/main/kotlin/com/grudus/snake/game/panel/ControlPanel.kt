package com.grudus.snake.game.panel

import com.grudus.snake.LifeCyclePanel
import com.grudus.snake.game.GamePanel
import com.grudus.snake.game.Speed
import com.grudus.snake.utils.Colors
import com.grudus.snake.utils.FontUtils
import java.awt.Dimension
import java.awt.GridBagConstraints
import java.awt.GridBagConstraints.LINE_END
import java.awt.GridBagConstraints.LINE_START
import java.awt.GridBagLayout
import javax.swing.JLabel
import javax.swing.Timer

class ControlPanel(private val gamePanel: GamePanel, val startSpeed: Speed, val initialSize: Int) : LifeCyclePanel() {
    private val points = JLabel()
    private val pointsLabel = JLabel("Points: ")
    private val time = JLabel()
    private val timeLabel = JLabel("Time: ")
    private val speed = JLabel()
    private val speedLabel = JLabel("Speed: ")
    private val size = JLabel()
    private val sizeLabel = JLabel("Size: ")

    private val grid = GridBagConstraints()
    private val timer = Timer(1000, { updateTime() })

    private var timeInSeconds = 0

    init {
        val size = Dimension(1000, 80)
        setSize(size)
        preferredSize = size
        layout = GridBagLayout()
        setupComponents()
        background = Colors.MENU_BACKGROUND
    }

    override fun onInit() {
        timeInSeconds = 0
        size.text = initialSize.toString()
        time.text = "0"
        points.text = "0"
        speed.text = startSpeed.label
        timer.start()
    }

    override fun onPause() {
        timer.stop()
    }

    override fun onResume() {
        timer.start()
    }

    override fun onEnd() {
        timer.stop()
    }

    fun getPoints() = points.text.toInt()

    fun updateSpeed(speed: Speed) {
        this.speed.text = speed.label
    }

    fun updateSnakeSize(bodyLength: Int) {
        this.size.text = bodyLength.toString()
    }

    private fun updateTime() {
        time.text = timeInSeconds.toString()
        timeInSeconds++
    }

    fun addPoints(points: Int) {
        val current = this.points.text.toInt()
        val actual = current + points
        this.points.text = actual.toString()
    }

    private fun setupComponents() {
        grid.weightx = 0.5
        grid.gridy = 0
        arrayOf(pointsLabel, points, speedLabel, speed, timeLabel, time, sizeLabel, size).forEachIndexed { index, label ->
            label.font = FontUtils.roboto(16)
            grid.gridx = index
            grid.anchor = if (index % 2 == 0) LINE_END else LINE_START
            add(label, grid)
        }
    }
}