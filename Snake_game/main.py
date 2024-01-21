from turtle import Screen, Turtle
from snake import Snake
from food import Food
from scoreboard import Scoreboard
from tkinter import *
from tkinter import messagebox
import time

# The code below set up the screen where the game take place
screen = Screen()
screen.setup(width=600, height=600)
screen.bgcolor("black")
screen.title("My Snake Game")
screen.tracer(0)

messagebox.showinfo(title="Instructions", message="The goal of this program is to let the snake eat as much food as possible. The more the snake eat, the more you score. You lose if the snake touches the wall or its own tail.")

# The code below create different objects for each classes
snake = Snake()
food = Food()
scoreboard = Scoreboard()

# The code below allow the user to move the snake using different keys
screen.listen()
screen.onkey(snake.up,"Up")
screen.onkey(snake.down,"Down")
screen.onkey(snake.left,"Left")
screen.onkey(snake.right,"Right")
game_is_on = True

# loops until the player loses
while game_is_on:
    screen.update()
    time.sleep(0.1)
    snake.move()


#Detect collision with food
    if snake.head.distance(food) < 15:
        food.refresh()
        snake.extend()
        scoreboard.increase_score()

# Detect collision with wall
    if snake.head.xcor() > 280 or snake.head.xcor() < -280 or snake.head.ycor() > 280 or snake.head.ycor() < -280 :
        scoreboard.reset()
        snake.reset()

# Detect collision with tail
    for segment in snake.segments[1:]: # checks if the snake has touched its own body except its head.

        if snake.head.distance(segment) < 10:
            scoreboard.reset()
            snake.reset()

screen.exitonclick()