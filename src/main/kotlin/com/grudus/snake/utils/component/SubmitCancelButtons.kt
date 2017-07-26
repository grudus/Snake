package com.grudus.snake.utils.component

import javax.swing.JButton
import javax.swing.JPanel

class SubmitCancelButtons(private val onSubmit: () -> Unit,
                          private val onCancel: () -> Unit,
                          submitText: String = "Submit",
                          cancelText: String = "Cancel") : JPanel() {

    private val submitButton = JButton(submitText)
    private val cancelButton = JButton(cancelText)

    init {
        add(submitButton)
        add(cancelButton)

        submitButton.addActionListener { _ -> onSubmit() }
        cancelButton.addActionListener { _ -> onCancel() }
    }
}