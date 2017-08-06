package com.grudus.snake.utils

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.ShouldSpec
import org.apache.commons.lang3.RandomStringUtils.randomAlphabetic

class StringFunctionsTest : ShouldSpec() {

    init {


        should("Truncate string when more than 12 chars") {
            val string = randomAlphabetic(55)
            val truncated = string.truncate(12)

            truncated.length shouldBe 15
            truncated shouldBe "..." + string.substring(55 - 12)
        }

        should("Return string when smaller than truncated length") {
            val string = randomAlphabetic(11)
            val truncated = string.truncate(12)

            truncated shouldBe string
        }

        should("Detect null string") {
            isNullOrEmpty(null) shouldBe true
        }

        should("Detect empty string when only whitespaces are present") {
            isNullOrEmpty("  \t  \n") shouldBe true
        }

        should("Detect empty string when no value is present") {
            isNullOrEmpty("") shouldBe true
        }

        should("Not detect empty string when value is present") {
            isNullOrEmpty(randomAlphabetic(11) + "\t") shouldBe false
        }
    }

}
