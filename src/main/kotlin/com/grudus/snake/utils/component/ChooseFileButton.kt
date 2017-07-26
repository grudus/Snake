package com.grudus.snake.utils.component

import com.grudus.snake.utils.FontUtils
import com.grudus.snake.utils.hasExtension
import com.grudus.snake.utils.truncate
import java.io.File
import javax.swing.JButton
import javax.swing.JFileChooser
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.filechooser.FileNameExtensionFilter

class ChooseFileButton(text: String) : JPanel() {
    private val button = JButton(text)
    private val label = JLabel()
    private var currentDir: File? = null
    private var selectedFile: File? = null

    init {
        initViews()

        button.addActionListener { _ ->
            if (!isEnabled) return@addActionListener

            val chooser = JFileChooser()
            chooser.currentDirectory = currentDir
            chooser.fileFilter = FileNameExtensionFilter("sn maps (*.sn)", "sn")

            val selected = chooser.showOpenDialog(this)
            if (selected == JFileChooser.APPROVE_OPTION && chooser.selectedFile.hasExtension("sn"))
                handleSelectedFile(chooser)
        }
    }

    private fun initViews() {
        add(button)
        add(label)
        button.font = FontUtils.roboto(button.font.size)
        label.font = FontUtils.roboto(label.font.size)
    }

    private fun handleSelectedFile(chooser: JFileChooser) {
        label.text = chooser.selectedFile.path.truncate(12)
        currentDir = chooser.currentDirectory
        selectedFile = chooser.selectedFile
    }

    fun setSelectedFile(path: String) {
        selectedFile = File(path)
        currentDir = selectedFile?.parentFile
        label.text = selectedFile?.path?.truncate(12)
    }

    fun getFile() = selectedFile
}