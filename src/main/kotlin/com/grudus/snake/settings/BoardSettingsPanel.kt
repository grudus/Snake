package com.grudus.snake.settings

import com.grudus.snake.game.Index
import com.grudus.snake.utils.Colors
import com.grudus.snake.utils.FontUtils
import com.grudus.snake.utils.component.ChooseFileButton
import com.grudus.snake.utils.component.NumericalTextField
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.Insets
import java.awt.event.ActionListener
import javax.swing.JCheckBox
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.border.LineBorder
import javax.swing.border.TitledBorder

class BoardSettingsPanel(val settingsPanel: SettingsPanel, val settings: CurrentSettings) : JPanel() {
    private val rowsLabel = JLabel("Rows: ")
    private val rows = NumericalTextField(3)
    private val columnsLabel = JLabel("Columns: ")
    private val columns = NumericalTextField(3)
    private val fileLable = JLabel("Load custom map")
    private val fileCheckbox = JCheckBox()
    private val fileButton = ChooseFileButton("Select")
    private val grid = GridBagConstraints()

    private var currentState = State.DEFAULT

    init {
        initState()
        val solidBorder = LineBorder(Colors.BORDER_COLOR)
        border = TitledBorder(solidBorder, "Board", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, FontUtils.roboto(12), Colors.BORDER_TITLE_COLOR)
        layout = GridBagLayout()

        arrayOf(rowsLabel, columnsLabel).forEach { it.font = FontUtils.roboto(it.font.size) }
        arrayOf(columns, rows).forEach { it.font = FontUtils.roboto(it.font.size) }

        fileCheckbox.addActionListener{_ ->
            currentState = if (fileCheckbox.isSelected) State.CUSTOM_MAP else State.DEFAULT
            updateDisabledComponents()
        }

        rows.addActionListener(numericKeyListener())
        columns.addActionListener(numericKeyListener())

        initView()
        updateDisabledComponents()
    }

    private fun initState() {
        if (this.settings.boardFilePath != null) {
            fileButton.setSelectedFile(this.settings.boardFilePath!!)
            fileCheckbox.isSelected = true
            currentState = State.CUSTOM_MAP
        }
        updateDisabledComponents()

    }

    fun numericKeyListener(): ActionListener {
        return ActionListener { e ->
            println(e)
        }
    }

    private fun updateDisabledComponents() {
        when (currentState) {
            State.CUSTOM_MAP -> {
                rows.isEditable = false
                columns.isEditable = false
                fileButton.isEnabled = true
            }
            State.DEFAULT -> {
                rows.isEditable = true
                columns.isEditable = true
                fileButton.isEnabled = false
            }
        }
    }

    private enum class State {DEFAULT, CUSTOM_MAP }

    fun save(settings: CurrentSettings) {
        when(currentState) {
            State.CUSTOM_MAP -> {
                settings.boardFilePath = fileButton.getFile()?.absolutePath
                settings.boardSize = null
            }
            State.DEFAULT -> {
                settings.boardFilePath = null
                settings.boardSize = Index(rows.text.toInt(), columns.text.toInt())
            }
        }
    }

    private fun initView() {
        val bigPadding = 24
        val smallPadding = 4
        grid.weightx = 0.5
        grid.insets = Insets(bigPadding, bigPadding, smallPadding, smallPadding)
        grid.gridx = 0
        grid.gridy = 0

        grid.anchor = GridBagConstraints.LINE_END
        add(rowsLabel, grid)

        grid.insets.top = smallPadding
        grid.insets.bottom = smallPadding
        grid.gridy = 1
        add(columnsLabel, grid)

        grid.insets.top = bigPadding
        grid.insets.bottom = smallPadding
        grid.insets.left = smallPadding
        grid.insets.right = bigPadding
        grid.anchor = GridBagConstraints.LINE_START
        grid.gridx = 1
        grid.gridy = 0
        add(rows, grid)

        grid.insets.top = smallPadding
        grid.insets.bottom = smallPadding
        grid.gridy = 1
        add(columns, grid)

        grid.gridx = 0
        grid.gridy = 2
        grid.anchor = GridBagConstraints.LINE_END
        add(fileLable, grid)

        grid.gridx = 1
        grid.gridy = 2
        grid.insets.left = bigPadding
        grid.insets.right = smallPadding
        grid.anchor = GridBagConstraints.LINE_START
        fileCheckbox.font = FontUtils.roboto(fileCheckbox.font.size)
        add(fileCheckbox, grid)

        grid.anchor = GridBagConstraints.CENTER
        grid.gridx = 0
        grid.gridwidth = 2
        grid.gridy = 3
        grid.insets.bottom = bigPadding
        grid.insets.left = smallPadding
        grid.insets.right = bigPadding
        add(fileButton, grid)
    }
}
