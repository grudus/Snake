package com.grudus.snake.event.game

import com.grudus.snake.event.EventBus
import com.grudus.snake.event.EventListener
import com.grudus.snake.game.GamePanel

class GameEventListener(private val gamePanel: GamePanel): EventListener() {

    override fun startListening() {
        EventBus.listen(NewGameEvent::class.java).subscribe {
            log.debug("Request for new game")
            gamePanel.onInit()
        }

        EventBus.listen(PauseEvent::class.java).subscribe {
            log.debug("Request for pause")
            gamePanel.onPause()
        }

        EventBus.listen(ResumeEvent::class.java).subscribe {
            log.debug("Request for resume after pause")
            gamePanel.onResume()
        }


        EventBus.listen(GameEndEvent::class.java).subscribe {
            log.debug("Game over :(")
            gamePanel.onEnd()
        }

        EventBus.listen(FoodEatenEvent::class.java).subscribe {
            log.debug("Food ${it.food} eaten")
            gamePanel.addPoints(it.food.points)
        }

        EventBus.listen(ChangeSpeedEvent::class.java).subscribe {
            log.debug("change speed to " + it.currentSpeed)
            gamePanel.updateTime(it.currentSpeed)
        }

        EventBus.listen(UpdateSnakeSizeEvent::class.java).subscribe {
            log.debug("change snake size to " + it.currentSize)
            gamePanel.updateSnakeSize(it.currentSize)
        }
    }
}