package com.grudus.snake.utils

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.ShouldSpec
import javax.swing.Timer

class TimerFunctionsTest : ShouldSpec() {
    init {

        should("Run only one time") {
            var timerInvocations = 0
            val delay = 10

            Timer(delay) {
                timerInvocations++
            }.runOnce()

            Thread.sleep(10L * delay)
            timerInvocations shouldBe 1
        }


    }
}