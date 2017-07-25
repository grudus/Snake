package com.grudus.snake.utils.component

import com.grudus.snake.utils.Colors
import javax.swing.JTextField
import javax.swing.border.LineBorder
import javax.swing.text.AttributeSet
import javax.swing.text.Document
import javax.swing.text.DocumentFilter
import javax.swing.text.PlainDocument

class NumericalTextField(doc: Document?, text: String?, columns: Int) : JTextField(doc, text, columns) {
    constructor(text: String?) : this(null, text, 0)
    constructor(columns: Int) : this(null, null, columns)
    constructor(text: String?, columns: Int) : this(null, text, columns)
    constructor() : this(null, null, 0)

    private val errorBorder = LineBorder(Colors.ERROR)
    private val normalBorder = super.getBorder()

    init {
        (document as PlainDocument).documentFilter = object : DocumentFilter() {
            override fun replace(fb: FilterBypass?, offset: Int, length: Int, insertedText: String?, attrs: AttributeSet?) {
                if (insertedText != null && isNumber(insertedText)) {
                    super.replace(fb, offset, length, insertedText, attrs)
                    normalBorder()
                }
                else errorBorder()
            }

            override fun remove(fb: FilterBypass?, offset: Int, length: Int) {
                super.remove(fb, offset, length)
                normalBorder()
            }
        }
    }

    private fun errorBorder() {
        this.border = errorBorder
    }

    private fun normalBorder() {
        this.border = normalBorder
    }

    private fun isNumber(text: String) = text.all { Character.isDigit(it) }


}