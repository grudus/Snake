package com.grudus.snake.settings

import com.grudus.snake.game.Index
import com.grudus.snake.utils.Colors
import com.grudus.snake.utils.FontUtils
import com.grudus.snake.utils.component.ChooseFileButton
import com.grudus.snake.utils.component.NumericalTextField
import com.grudus.snake.utils.isNullOrEmpty
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.Insets
import javax.swing.JCheckBox
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField
import javax.swing.border.LineBorder
import javax.swing.border.TitledBorder

class BoardSettingsPanel(val settings: Settings) : JPanel(), ChangingSettings {
    private val rowsLabel = JLabel("Rows: ")
    private val rows = NumericalTextField(3)
    private val columnsLabel = JLabel("Columns: ")
    private val columns = NumericalTextField(3)
    private val fileLabel = JLabel("Load custom map")
    private val fileCheckbox = JCheckBox()
    private val fileButton = ChooseFileButton("Select")
    private val grid = GridBagConstraints()

    private var currentState = State.DEFAULT

    init {
        initState()
        border = createBorder()
        initView()
        updateDisabledComponents()
    }

    override fun isChangePossible(): Boolean {
        return currentState.validate(rows, columns, fileButton)
    }

    override fun updateSettings() {
        currentState.save(settings, rows, columns, fileButton)
    }

    private fun createBorder() = TitledBorder(LineBorder(Colors.BORDER_COLOR),
                "Board",
                TitledBorder.LEADING,
                TitledBorder.DEFAULT_POSITION,
                FontUtils.roboto(12),
                Colors.BORDER_TITLE_COLOR)

    private fun initState() {
        if (!isNullOrEmpty(this.settings.boardFilePath)) {
            fileButton.setSelectedFile(this.settings.boardFilePath!!)
            fileCheckbox.isSelected = true
            currentState = State.CUSTOM_MAP
        }
        else if (this.settings.boardSize != null) {
            rows.text = this.settings.boardSize?.row.toString()
            columns.text = this.settings.boardSize?.column.toString()
        }
        updateDisabledComponents()
    }


    private fun updateDisabledComponents() = currentState.updateDisabledComponents(rows, columns, fileButton)

    private fun initView() {
        layout = GridBagLayout()
        arrayOf(rowsLabel, columnsLabel).forEach { it.font = FontUtils.roboto(it.font.size) }
        arrayOf(columns, rows).forEach { it.font = FontUtils.roboto(it.font.size) }

        val wrapper = createWrapper()

        grid.insets = Insets(4, 4, 4, 4)
        grid.weightx = 0.5
        grid.anchor = GridBagConstraints.LINE_START

        grid.gridx = 0
        grid.gridy = 0
        wrapper.add(rowsLabel, grid)

        grid.gridy = 1
        wrapper.add(columnsLabel, grid)

        grid.gridy = 2
        wrapper.add(fileLabel, grid)

        grid.gridx = 1
        decorateCheckbox()
        wrapper.add(fileCheckbox, grid)

        grid.gridy = 1
        wrapper.add(columns, grid)

        grid.gridy = 0
        wrapper.add(rows, grid)

        grid.gridx = 0
        grid.gridwidth = 2
        grid.gridy = 3
        wrapper.add(fileButton, grid)
    }

    private fun decorateCheckbox() {
        fileCheckbox.font = FontUtils.roboto(fileCheckbox.font.size)
        fileCheckbox.addActionListener { _ ->
            currentState = if (fileCheckbox.isSelected) State.CUSTOM_MAP else State.DEFAULT
            updateDisabledComponents()
        }
    }


    private fun createWrapper(): JPanel {
        val panel = JPanel()
        panel.layout = GridBagLayout()
        grid.insets = Insets(24, 24, 24, 24)
        add(panel, grid)
        return panel
    }

    private enum class State {
        DEFAULT {
            override fun save(settings: Settings, rows: JTextField, columns: JTextField, fileButton: ChooseFileButton) {
                settings.boardFilePath = null
                settings.boardSize = Index(rows.text.toInt(), columns.text.toInt())
            }
            override fun updateDisabledComponents(rows: JTextField, columns: JTextField, fileButton: ChooseFileButton) {
                rows.isEditable = true
                columns.isEditable = true
                fileButton.isEnabled = false
            }

            override fun validate(rows: NumericalTextField, columns: NumericalTextField, fileButton: ChooseFileButton) =
                    listOf(columns, rows).filter { it.text.isBlank() }.onEach { it.showError() }.isEmpty()

        },
        CUSTOM_MAP {
            override fun save(settings: Settings, rows: JTextField, columns: JTextField, fileButton: ChooseFileButton) {
                settings.boardFilePath = fileButton.getFile()?.absolutePath
                settings.boardSize = null
            }
            override fun updateDisabledComponents(rows: JTextField, columns: JTextField, fileButton: ChooseFileButton) {
                rows.isEditable = false
                columns.isEditable = false
                fileButton.isEnabled = true
            }
            override fun validate(rows: NumericalTextField, columns: NumericalTextField, fileButton: ChooseFileButton) =
                    fileButton.getFile() != null && fileButton.getFile()!!.exists()
        };
        abstract fun save(settings: Settings, rows: JTextField, columns: JTextField, fileButton: ChooseFileButton)
        abstract fun updateDisabledComponents(rows: JTextField, columns: JTextField, fileButton: ChooseFileButton)
        abstract fun  validate(rows: NumericalTextField, columns: NumericalTextField, fileButton: ChooseFileButton): Boolean
    }
}
