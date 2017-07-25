package com.grudus.snake.event

import com.grudus.snake.game.Speed
import com.grudus.snake.game.entity.food.Food

class NewGameEvent
class PauseEvent
class ResumeEvent
class GameEndEvent
class FoodEatenEvent(val food: Food)
class ChangeSpeedEvent(val currentSpeed: Speed)
class UpdateSnakeSizeEvent(val currentSize: Int)