package com.grudus.snake.utils

import io.kotlintest.matchers.shouldBe
import io.kotlintest.matchers.shouldNotBe
import io.kotlintest.specs.ShouldSpec
import org.apache.commons.lang3.RandomStringUtils.randomAlphabetic

class CollectionsFunctionsTest : ShouldSpec() {

    init {
        val value: String = randomAlphabetic(11)
        val key: String = randomAlphabetic(4)

        should("Invoke function and add value to map if not already present") {
            val map = mutableMapOf<String, String>()
            val function: () -> String = { value }
            map.invokeAndPutIfAbsent(key, function)

            map[key] shouldBe value
        }

        should("Return newly inserted value") {
            val map = mutableMapOf<String, String>()
            val function = { value }
            val inserted = map.invokeAndPutIfAbsent(key, function)

            inserted shouldBe value
        }

        should("Return old value if no insert was needed") {
            val map = mutableMapOf(key to value)
            val function = { randomAlphabetic(55) }
            val inserted = map.invokeAndPutIfAbsent(key, function)

            inserted shouldBe value
        }

        should("Not invoke function when value is presented") {
            val map = mutableMapOf(key to randomAlphabetic(3))
            val function = { throw IllegalStateException("Invoked function") }
            map.invokeAndPutIfAbsent(key, function)

            map[key] shouldNotBe value
        }
    }
}