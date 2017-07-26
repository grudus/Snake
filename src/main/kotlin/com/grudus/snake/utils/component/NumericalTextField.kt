package com.grudus.snake.utils.component

import com.grudus.snake.utils.Colors
import com.grudus.snake.utils.runOnce
import javax.swing.JTextField
import javax.swing.Timer
import javax.swing.border.LineBorder
import javax.swing.text.AttributeSet
import javax.swing.text.Document
import javax.swing.text.DocumentFilter
import javax.swing.text.PlainDocument

class NumericalTextField(doc: Document?, text: String?, columns: Int) : JTextField(doc, text, columns) {
    private val errorBorder = LineBorder(Colors.ERROR)
    private val normalBorder = super.getBorder()

    constructor(columns: Int) : this(null, null, columns)

    init {
        (document as PlainDocument).documentFilter = object : DocumentFilter() {
            override fun replace(fb: FilterBypass?, offset: Int, length: Int, insertedText: String?, attrs: AttributeSet?) {
                if (isNumber(insertedText))
                    super.replace(fb, offset, length, insertedText, attrs)
                else
                    showErrorBorder()
            }
        }
    }

    fun showError() = showErrorBorder(1000)

    private fun showErrorBorder(delay: Int = 100) {
        border = errorBorder
        Timer(delay) {
            border = normalBorder
        }.runOnce()
    }

    private fun isNumber(text: String?) = text != null && text.all { Character.isDigit(it) }
}