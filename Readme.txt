This is the Dungeons Of Doom game.

Firstly, when introduced to the game, select the type of map you want.  You can choose from small, medium, large.  These are preloaded maps.  If you enter an invalid name, the program will automatically create a default map to play on.

The map is made up of these symbols: '#', '.', 'G', and 'E'.  The hashtag represents a wall that cannot be used as a space for a player.  The G is where gold is collected. The E is an exit space, from where you can quit the game.  The '.' is an empty space, free to move to.

The aim of the game is to collect enough gold and quit the game before a bot catches you.  The bot's only purpose is catching you. 

- To collect the gold, you must be on a space that has a G, and then pick it up, using the 'PICKUP' command.  

- To move, you must say 'MOVE', then 'NORTH'/'EAST'/SOUTH/'WEST' in the same line.  

- To see what's in your nearby surroundings, type 'LOOK'.  Nearby surroundings are classified as a 5x5 grid around your current position.  This uses a turn.

- To see how much gold you have, type 'GOLD'.

- To see how much gold you need before you can exit to win, type 'HELLO'.

- To end the game, you must be on an 'E' space, and then you must type 'QUIT'.  If you have enough gold at this point, you will win, otherwise you will lose.

Typing anything into the code will use a go, even if the command is incorrect, and then give the bot a go.



------ IMPLEMENTATION ------

The classes I have used are:

Player
HumanPlayer
BotPlayer
Map
GameLogic


HumanPlayer and BotPlayer both inherit from Player, but have their own methods that are specialised to their requirements.  They pass commands to GameLogic, which processes and executes them.  The main processing happens in the runGame() method which is called in the Main method.



-- BOT DETAILS -- 

If the bot is spawned far away from the player, then it is likely that the bot will never see the player using LOOK, and therefore never make a well-informed decision.  Therefore, I introduced a random number that may force the bot to randomly move in a direction in the event that it cannot see the player.  If the bot can see the player however, it will try to get closer, first along the x-axis, then it will adjust its y-axis position on the map.