from turtle import Turtle
alignement = "center"
font= ("Courier", 23, "normal")

class Scoreboard(Turtle):

    def __init__(self): # initialize the variables
        super().__init__()
        self.score = 0
        with open("data.txt") as data:
           self.high_score = int(data.read())
        self.color("white")
        self.penup()
        self.goto(0, 270)
        self.hideturtle()
        self.update_scoreboard()

    def update_scoreboard(self): # updates the scoreboard
        self.clear()
        self.write(f"Score: {self.score} High Score: { self.high_score}", align=alignement , font=font)

    def reset(self): # resets the game when the player lost
        if self.score > self.high_score:
            self.high_score = self.score
        with open("data.txt", mode = "w") as data:
            data.write(f"{self.high_score}")
        self.score = 0
        self.update_scoreboard()


    def increase_score(self): # increases the score each time the snake eat the food
        self.score += 1
        self.clear()
        self.update_scoreboard()
