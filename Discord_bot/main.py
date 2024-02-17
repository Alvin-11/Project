import discord
import os
import requests
import json
import random
from better_profanity import profanity

global randnumber
randnumber = 0

intents = discord.Intents.default()
intents.message_content = True

client = discord.Client(intents=intents)

event_name = "hello"
event_description = ""
event_time = ""

def get_quote(): # get different quotes when asked to
  response = requests.get("https://zenquotes.io/api/random")
  json_data = json.loads(response.text)
  quote = json_data[0]['q'] + " -" + json_data[0]['a']
  return(quote)
@client.event
async def on_ready():
    print('We have logged in as {0.user}'.format(client))

@client.event
async def on_member_join(member):
    await member.send(f"Welcome {member.name}. You have officially joined this cool server. Feel free to use all the resources available in this discord channel.")

@client.event
async def on_message(message):
    pass
@client.event
async def on_message(message):
    if message.author == client.user:
        return

    if message.content.startswith('$inspire'):
        quote = get_quote()
        await message.channel.send(quote)



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
async def play_game(message):
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

def check_for_profanity(text): # profanity checker
    if profanity.contains_profanity(text):
        return True
    else:
        return False


@client.event
async def poll(ctx, message, *options: str): # This function lets the bot give and take the values of a poll
    if len(options) <= 1:
        await client.say("```Error! A poll must have more than one option.```")
        return
    if len(options) > 5:
        await client.say("```Error! Poll can have no more than five options.```")
        return

        # Construct the poll message
    poll_message = f"{message}\n"
    for i, option in enumerate(options):
        poll_message += f"choice {i}: {option}\n"

    # Send the poll message
    poll = await ctx.send(poll_message)

    # Add reactions for each option
    for i in range(len(options)):
        await poll.add_reaction(chr(127462 + i))

@client.event
async def on_reaction_add(reaction, user): #  This function lets the bot give a reaction after the polls
    if user == client.user:
        return

    message = reaction.message
    channel = message.channel

    # Check if the message is a poll
    if message.author == client.user:
        # Get the index of the option from the reaction
        option_index = ord(reaction.emoji) - 127462

        # Modify the poll message to update the vote count
        lines = message.content.split('\n')
        lines[option_index + 2] += f" - {reaction.count - 1} votes"

        # Edit the poll message
        await message.edit(content='\n'.join(lines))


channel_id = ""
@client.event
async def event(): # This is where the event info is given to everyone in the channel
    channel = client.get_channel(channel_id)
    await channel.send(f" {event_name} \n\n Description: {event_description}\n Time: {event_time}")

client.run("Discord_ID")

