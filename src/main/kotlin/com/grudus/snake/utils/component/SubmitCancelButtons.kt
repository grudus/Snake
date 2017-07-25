package com.grudus.snake.utils.component

import javax.swing.JButton
import javax.swing.JPanel
import javax.swing.border.EmptyBorder

class SubmitCancelButtons(private val onSubmit: () -> Unit,
                          private val onCancel: () -> Unit,
                          private val submitText: String = "Submit",
                          private val cancelText: String = "Cancel") : JPanel() {

    private val submitButton = JButton(submitText)
    private val cancelButton = JButton(cancelText)

    init {
        border = EmptyBorder(10, 10, 10, 10)

        add(submitButton)
        add(cancelButton)

        submitButton.addActionListener { _ -> onSubmit() }
        cancelButton.addActionListener { _ -> onCancel() }
    }
}