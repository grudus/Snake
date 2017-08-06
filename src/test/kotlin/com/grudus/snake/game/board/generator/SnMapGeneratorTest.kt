package com.grudus.snake.game.board.generator

import com.grudus.snake.utils.exceptions.ReadMapException
import io.kotlintest.matchers.shouldBe
import io.kotlintest.matchers.shouldThrow
import io.kotlintest.specs.ShouldSpec
import java.io.File

class SnMapGeneratorTest : ShouldSpec() {
    private val VALID_MAP = "maps/map.sn"
    private val WITHOUT_EXTENSION_MAP = "maps/without_extension.txt"
    private val INVALID_MAP = "maps/invalid.sn"
    private val EMPTY_MAP = "maps/empty.sn"

    init {

        should("generate valid board") {
            val file = file(VALID_MAP)

            val board = SnMapGenerator().generate(file)

            board.columns shouldBe 5
            board.rows shouldBe 7
        }

        should("throw exception when file hasn't got valid extension") {

            shouldThrow<IllegalArgumentException> { SnMapGenerator().generate(file(WITHOUT_EXTENSION_MAP)) }
        }

        should("throw exception when file with invalid characters") {

            shouldThrow<ReadMapException> { SnMapGenerator().generate(file(INVALID_MAP)) }
        }

        should("throw exception when reading empty file") {

            shouldThrow<ReadMapException> { SnMapGenerator().generate(file(EMPTY_MAP)) }
        }
    }

    private fun file(path: String) = File(this.javaClass.classLoader.getResource(path).path)
}