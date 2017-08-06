package com.grudus.snake.utils

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.ShouldSpec
import org.apache.commons.lang3.RandomStringUtils.randomAlphabetic
import java.io.File

class FileFunctionsTest: ShouldSpec() {
    init {

        should("detect file extension") {
            val extension = randomAlphabetic(3)
            val file = File("${randomAlphabetic(11)}.$extension")

            file.extension shouldBe extension
            file.hasExtension(extension) shouldBe true
        }

    }
}