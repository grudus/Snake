package com.grudus.snake.event.game

import com.grudus.snake.game.Speed
import com.grudus.snake.game.entity.food.Food

class NewGameEvent
class PauseEvent
class ResumeEvent
class GameEndEvent(val reason: String)
class FoodEatenEvent(val food: Food)
class ChangeSpeedEvent(val currentSpeed: Speed)
class UpdateSnakeSizeEvent(val currentSize: Int)