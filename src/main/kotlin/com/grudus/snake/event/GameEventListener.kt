package com.grudus.snake.event

import com.grudus.snake.game.GamePanel

class GameEventListener(private val gamePanel: GamePanel) {

    fun startListening() {
        EventBus.listen(NewGameEvent::class.java).subscribe {
            println("Request for new game")
            gamePanel.onInit()
        }

        EventBus.listen(PauseEvent::class.java).subscribe {
            println("Request for pause")
            gamePanel.onPause()
        }

        EventBus.listen(ResumeEvent::class.java).subscribe {
            println("Request for resume after pause")
            gamePanel.onResume()
        }


        EventBus.listen(GameEndEvent::class.java).subscribe {
            println("Game over :(")
            gamePanel.onEnd()
        }

        EventBus.listen(FoodEatenEvent::class.java).subscribe {
            println("Food ${it.food} eaten")
            gamePanel.addPoints(it.food.points)
        }

        EventBus.listen(ChangeSpeedEvent::class.java).subscribe {
            println("change speed to " + it.currentSpeed)
            gamePanel.updateTime(it.currentSpeed)
        }

        EventBus.listen(UpdateSnakeSizeEvent::class.java).subscribe {
            println("change snake size to " + it.currentSize)
            gamePanel.updateSnakeSize(it.currentSize)
        }
    }
}