import discord
import os
import requests
import json
import random
from better_profanity import profanity
import asyncio  # Import asyncio module

global randnumber
randnumber = 0
API_KEY = "API_KEY"

intents = discord.Intents.default()
intents.message_content = True

client = discord.Client(intents=intents)

event_name = "hello"
event_description = ""
event_time = ""

@client.event 
async def get_quote(): # get different quotes when asked to
  response = requests.get("https://zenquotes.io/api/random")
  json_data = json.loads(response.text)
  quote = json_data[0]['q'] + " -" + json_data[0]['a']
  return(quote)
@client.event
async def on_ready():
    print('We have logged in as {0.user}'.format(client))

@client.event
async def on_member_join(member):
    await member.send(f"""Welcome {member.name}. You have officially joined this cool server. 
                            Feel free to use all the resources available in this discord channel.""")


@client.event
async def on_message(message):
    if message.author == client.user:
        return

    if message.content.startswith('$weather'):# This code searches for the weather data and projects it to the user
        await message.channel.send(
            "Please enter the latitude and longitude coordinates separated by a comma (e.g., 40.7128, -74.0060):")

        def check(m):
            return m.author == message.author and m.channel == message.channel

        try:
            response = await client.wait_for('message', check=check, timeout=60)  # Wait for user input
            coordinates = response.content.strip().split(',')
            if len(coordinates) != 2:
                await message.channel.send("Invalid format. Please enter latitude and longitude separated by a comma.")
                return
            latitude, longitude = map(float, coordinates)

            weather_data = await get_weather(latitude, longitude)  # Await the result of get_weather
            if weather_data:
                await message.channel.send(f"Weather forecast for ({latitude}, {longitude}): {weather_data}")
            else:
                await message.channel.send("Failed to retrieve weather data.")
        except asyncio.TimeoutError:
            await message.channel.send("You took too long to respond. Weather forecast canceled.")





    if message.content.startswith('$poll'): # This checks for polls happening
        await message.channel.send("Please enter the poll question and options separated by commas.")

        def check(m):
            return m.author == message.author and m.channel == message.channel


        response = await client.wait_for('message')  # Wait for user input
        poll_data = response.content.split(',')  # Split user input by commas
        poll_question = poll_data[0].strip()  # Extract the poll question
        poll_options = [option.strip() for option in poll_data[1:]]  # Extract poll options

        if len(poll_options) < 2:
            await message.channel.send("Please provide at least two options for the poll.")
            return

        await message.channel.send("Creating the poll...")
        await create_poll(message.channel, poll_question, poll_options)  # Call function to create the poll

        total_reactions = sum([reaction.count for reaction in message.reactions])
        if total_reactions - 1 == len(client.users):
            # Get the poll result
            poll_result = await get_poll_result(message)
            await message.channel.send("Poll has ended! Here are the results:")
            for option, votes in poll_result.items():
                await message.channel.send(f"{option}: {votes} votes")


    if message.content.startswith('$inspire'):
        quote = get_quote()
        await message.channel.send(quote)

    if message.content.startswith('$poll'):
        await message.channel.send("Write the question and the two choices.")

        response1 = await client.wait_for('message')

        if response.content.lower() == "yes":
            await message.channel.send("Guess a number between 1 and 100.")
            await play_game(message)
        else:
            await message.channel.send("Maybe next time!")

    if message.content.startswith("game"): # play a number guessing game
        await message.channel.send("Do you want to play a number guessing game")


        response = await client.wait_for('message')


        if response.content.lower() == "yes":
            await message.channel.send("Guess a number between 1 and 100.")
            await play_game(message)
        else:
            await message.channel.send("Maybe next time!")

    user_greetings = ['hello', 'hi', 'hey','greetings', 'howdy', 'good morning', 'good afternoon', 'good evening']
    bot_greetings = ['hello', 'hi', 'hey', 'greetings', 'howdy']
    if message.content.lower() in user_greetings: # Responds to the greetings of the user
        await message.channel.send(random.choice(bot_greetings))

    if check_for_profanity(message.content):
        # Delete the inappropriate message
        await message.delete()
        # Optionally, notify the user that their message was deleted
        await message.author.send("Your message was deleted because it contained inappropriate content.")



# Function to check if a message contains inappropriate content

@client.event
async def play_game(message): # This plays a number guessing game with the user
    global randnumber
    randnumber = random.randint(1, 2)
    while True:
        try:
            guess_msg = await client.wait_for('message')
            guess = int(guess_msg.content)
            if guess == randnumber:
                await message.channel.send("Good job on guessing the correct number!")
                break
            else:
                await message.channel.send("Guess again or write 'abandon'.")
        except ValueError:
            await message.channel.send("Please enter a valid number.")

@client.event
async def check_for_profanity(text): # profanity checker
    if profanity.contains_profanity(text):
        return True
    else:
        return False

@client.event
async def get_poll_result(message): # Return the result of the poll
    poll_options = message.embeds[0].description.split("\n")
    results = {}

    for option_line in poll_options:
        option, votes = option_line.split(" - ")
        option = option.strip()
        votes = int(votes.split()[0])
        results[option] = votes

    return results

@client.event
async def create_poll(channel, question, options): # this creates a poll
    # Construct the poll message
    poll_message = f"{question}\n"
    for i, option in enumerate(options):
        poll_message += f"Option {i+1}: {option}\n"

    # Send the poll message
    poll = await channel.send(poll_message)

    # Add reactions for each option
    for i in range(len(options)):
        await poll.add_reaction(chr(127462 + i))

channel_id = ""
@client.event
async def event(): # This is where the event info is given to everyone in the channel
    channel = client.get_channel(channel_id)
    await channel.send(f" {event_name} \n\n Description: {event_description}\n Time: {event_time}")

@client.event
async def get_weather(lat,lon): # This gets the weather from the api
    url = f"http://api.openweathermap.org/data/2.5/forecast?lat={lat}&lon={lon}&appid={API_KEY}"
    response = requests.get(url)

    if response.status_code == 200:
        data = response.json()
        print("API Response Content:", data)
        forecast_list = data['list']

        # Iterate over each forecast item and print the temperature
        for forecast in forecast_list:
            temperature_kelvin = forecast['main']['temp']  # Temperature in Kelvin
            temperature_celsius = temperature_kelvin - 273.15  # Convert Kelvin to Celsius
            feels_like = forecast['main']['feels_like'] - 273.15
            humidity = forecast['main']['humidity']
            pressure = forecast['main']['pressure']

            return f"""
            Temperature: {round(temperature_celsius)} °C
            Feels_like: {round(feels_like)}°C
            Humidity: {round(humidity)} %
            Pressure: {round(pressure)} hPa"""
    else:
        pass

client.run("Discord_API")

