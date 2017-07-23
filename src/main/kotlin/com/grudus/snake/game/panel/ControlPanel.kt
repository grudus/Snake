package com.grudus.snake.game.panel

import com.grudus.snake.game.GamePanel
import com.grudus.snake.game.LifeCycle
import com.grudus.snake.game.Speed
import com.grudus.snake.utils.Colors
import com.grudus.snake.utils.FontUtils
import java.awt.Dimension
import java.awt.GridBagConstraints
import java.awt.GridBagConstraints.LINE_END
import java.awt.GridBagConstraints.LINE_START
import java.awt.GridBagLayout
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.Timer

class ControlPanel(private val gamePanel: GamePanel, val startSpeed: Speed) : JPanel(), LifeCycle {
    private val points = JLabel("0")
    private val pointsLabel = JLabel("Points: ")
    private val time = JLabel("0")
    private val timeLabel = JLabel("Time: ")
    private val speed = JLabel(startSpeed.label)
    private val speedLabel = JLabel("Speed: ")
    private val size = JLabel("5")
    private val sizeLabel = JLabel("Size: ")

    private val grid = GridBagConstraints()
    private val timer = Timer(1000, {updateTime()})

    private var timeInSeconds = 0

    init {
        val size = Dimension(1000, 80)
        setSize(size)
        preferredSize = size
        layout = GridBagLayout()
        setupComponents()

        background = Colors.MENU_BACKGROUND
        timer.start()

    }

    override fun restart() {
        timeInSeconds = 0
        size.text = "0"
        time.text = "0"
        points.text = "0"
        speed.text = startSpeed.label
        timer.start()

    }

    override fun stop() {
        timer.stop()
    }

    fun updateSpeed(speed: Speed) {
        this.speed.text = speed.label
    }

    fun  updateSnakeSize(bodyLength: Int) {
        this.size.text = bodyLength.toString()
    }

    private fun updateTime() {
        time.text = timeInSeconds.toString()
        timeInSeconds++
    }

    private fun setupComponents() {
        grid.weightx = 0.5
        grid.gridy = 0
        arrayOf(pointsLabel, points, speedLabel, speed, timeLabel, time, sizeLabel, size).forEachIndexed{index, label ->
            label.font = FontUtils.roboto(16)
            grid.gridx = index
            grid.anchor = if (index % 2 == 0) LINE_END else LINE_START
            add(label, grid)
        }

    }

    fun  addPoints(points: Int) {
        val current = this.points.text.toInt()
        val actual = current + points
        this.points.text = actual.toString()
    }


}