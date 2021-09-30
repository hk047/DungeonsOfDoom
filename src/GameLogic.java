import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Random;

/**
 * Contains the main logic part of the game, as it processes.
 *
 */
public class GameLogic {

    private static BufferedReader br;
    private Map map1;
    private static HumanPlayer player1;
    private static BotPlayer bot1;
    private boolean running;


    /**
     * Default constructor
     */

    public GameLogic() {
        map1 = new Map();
        player1 = new HumanPlayer();
        bot1 = new BotPlayer();
    }

    //secondary constructor
    public GameLogic(String mapToBuild) {
        map1 = new Map(mapToBuild);
        player1 = new HumanPlayer();
        bot1 = new BotPlayer();
    }

    /**
     * Checks if the game is running
     *
     * @return if the game is running.
     */


    //gets the game state
    protected boolean gameRunning() {
        return running;
    }

    /**
     * Returns the gold required to win.
     *
     * @return : Gold required to win.
     */
    protected String hello() {
        int goldToWin = map1.getGoldRequired();
        String goldToWinOutput = "Gold to win: " + goldToWin;
        return goldToWinOutput;
    }

    /**
     * Returns the gold currently owned by the player.
     *
     * @return : Gold currently owned.
     */
    protected String gold() {
        int goldOwned = player1.getGold();
        String goldOutput = "Gold currently owned: " + goldOwned;
        return goldOutput;
    }

    /**
     * Checks if movement is legal and updates player's location on the map.
     *
     * @param direction : The direction of the movement.
     * @return : Protocol if success or not.
     */
    protected String move(char direction, boolean playerTurn) {

        //human's turn
        if (playerTurn) {
            if (direction == 'n') {
                char c = map1.getMap()[player1.getPositionY() - 1][player1.getPositionX()]; //reads the location that player wants to move to

                //if there is no wall
                if (c != '#') {
                    player1.setPositionY(player1.getPositionY() - 1); // moves the player to their desired location
                    return "success";
                } else {
                    return "failure";
                }
            } else if (direction == 'e') {
                char c = map1.getMap()[player1.getPositionY()][player1.getPositionX() + 1]; //reads the location that player wants to move to
                if (c != '#') {
                    player1.setPositionX(player1.getPositionX() + 1); // moves the player to their desired location
                    return "success";
                } else {
                    return "failure";
                }
            } else if (direction == 's') {
                char c = map1.getMap()[player1.getPositionY() + 1][player1.getPositionX()]; //reads the location that player wants to move to
                if (c != '#') {
                    player1.setPositionY(player1.getPositionY() + 1); // moves the player to their desired location
                    return "success";
                } else {
                    return "failure";
                }
            } else if (direction == 'w') {
                char c = map1.getMap()[player1.getPositionY()][player1.getPositionX() - 1]; //reads the location that player wants to move to
                if (c != '#') {
                    player1.setPositionX(player1.getPositionX() - 1); // moves the player to their desired location
                    return "success";
                } else {
                    return "failure";
                }
            }
            return "failure";
        }

        //bot's turn
        else {
            if (direction == 'n') {
                char c = map1.getMap()[bot1.getPositionY() - 1][bot1.getPositionX()]; //reads the location that bot wants to move to

                //if there is no wall
                if (c != '#') {
                    bot1.setPositionY(bot1.getPositionY() - 1);   // moves the bot to its desired location
                    return "success";
                } else {
                    return "failure";
                }
            } else if (direction == 'e') {
                char c = map1.getMap()[bot1.getPositionY()][bot1.getPositionX() + 1]; //reads the location that bot wants to move to
                if (c != '#') {
                    bot1.setPositionX(bot1.getPositionX() + 1);   // moves the bot to its desired location
                    return "success";
                } else {
                    return "failure";
                }
            } else if (direction == 's') {
                char c = map1.getMap()[bot1.getPositionY() + 1][bot1.getPositionX()]; //reads the location that bot wants to move to
                if (c != '#') {
                    bot1.setPositionY(bot1.getPositionY() + 1);    // moves the bot to its desired location
                    return "success";
                } else {
                    return "failure";
                }
            } else if (direction == 'w') {
                char c = map1.getMap()[bot1.getPositionY()][bot1.getPositionX() - 1]; //reads the location that bot wants to move to
                if (c != '#') {
                    bot1.setPositionX(bot1.getPositionX() - 1);   // moves the bot to its desired location
                    return "success";
                } else {
                    return "failure";
                }
            }
            return "failure";
        }
    }

    /**
     * Converts the map from a 2D char array to a single string.
     *
     * @return : A String representation of the game map.
     */
    protected String look(boolean playerTurn) {
        String mapString = "";
        int centreX = 0;
        int centreY = 0;
        boolean closeToPlayer = false;

        //human's turn
        if (playerTurn) {
            //centre coordinates of the 5x5 grid based on human's position
            centreX = player1.getPositionX();
            centreY = player1.getPositionY();
        }

        //bot's turn
        else {
            //centre coordinates of the 5x5 grid based on bot's position
            centreX = bot1.getPositionX();
            centreY = bot1.getPositionY();

            //if the player is in the view of the bot (in 5x5 grid)
            if ((centreX - 2 <= player1.getPositionX() && player1.getPositionX() <= centreX + 3)
                    && (centreY - 2 < player1.getPositionY() && player1.getPositionY() < centreX + 3)) {
                closeToPlayer = true;
            }
        }

        //generate 5x5 grid
        for (int i = centreY - 2; i < centreY + 3; i++) {
            for (int j = centreX - 2; j < centreX + 3; j++) {

                //checking if it goes beyond the boundaries of the map
                try {
                    if (i == bot1.getPositionY() && j == bot1.getPositionX()) {
                        mapString += 'B';
                    } else {
                        mapString += map1.getMap()[i][j];
                    }

                    //expected exception if boundaries are exceeded
                } catch (IndexOutOfBoundsException e) {
                    mapString += '#';
                }
            }
            mapString += "\n";
        }

        //if human's turn
        if (playerTurn) {
            mapString = mapString.substring(0, 14) + "P" + mapString.substring(15); //adds 'P' at the centre of the grid
            return mapString;
        }

        //(bot's turn) if bot can see human
        else if (closeToPlayer) {
            bot1.setSeenHumanPlayer(true);  //bot has seen human
            bot1.setDistanceFromHumanX(bot1.getPositionX() - player1.getPositionX());  //measures distance to human
            bot1.setDistanceFromHumanY(bot1.getPositionY() - player1.getPositionY());  //measures distance to human
            return "bot look successful";
        }
        bot1.setSeenHumanPlayer(false);  //bot hasn't seen human
        return "bot look successful";  //successfully completed 'look'
    }

    /**
     * Processes the player's pickup command, updating the map and the player's gold amount.
     *
     * @return If the player successfully picked-up gold or not.
     */
    protected String pickup() {

        //if current location has gold
        if (map1.getMap()[player1.getPositionY()][player1.getPositionX()] == 'G') {
            player1.setGold(1);
            map1.updateMap(player1.getPositionY(), player1.getPositionX(), '.'); //update the map to remove the gold
            return "success";
        } else {
            return "failure";
        }
    }

    /**
     * Quits the game, shutting down the application.
     */
    protected String quitGame(boolean botHumanMeet) {

        //if bot caught human
        if (botHumanMeet) {
            running = false;
            return "Bot caught you. LOSE.";
        }

        //if current position has an E
        if (map1.getMap()[player1.getPositionY()][player1.getPositionX()] == 'E') {
            running = false;

            //if human has enough gold to win on the map
            if (player1.getGold() >= map1.getGoldRequired()) {
                return "WIN";
            }

            //if human did not have enough gold
            else {
                return "LOSE";
            }
        }
        //if not on an E
        else {
            return "Not on an 'E', could not quit";
        }
    }

    //reads the player's input and determines which method to call
    protected String chooseAction(String playerAction, boolean playerTurn) {

        //processes the input
        if (playerAction.equals("hello")) {
            return hello();
        } else if (playerAction.equals("gold")) {
            return gold();
        } else if (playerAction.equals("pickup")) {
            return pickup();
        } else if (playerAction.equals("look")) {
            return look(playerTurn);
        } else if (playerAction.equals("quit")) {
            return quitGame(false);
        }

        //if the player's input starts with 'move'
        else if (playerAction.substring(0, 4).equals("move")) {

            if (playerAction.substring(4).equals(" north")) {
                return move('n', playerTurn);
            } else if (playerAction.substring(4).equals(" east")) {
                return move('e', playerTurn);
            } else if (playerAction.substring(4).equals(" south")) {
                return move('s', playerTurn);
            } else if (playerAction.substring(4).equals(" west")) {
                return move('w', playerTurn);
            }
        } else if (playerAction.equals("invalid")) {
            return playerAction;
        }
        return playerAction;
    }


    //main controlling of game
    protected static void runGame() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to Dungeons of Doom.  Choose map. (small, medium or large)");
        String mapChosen = "";
        boolean defaultMap;
        while (true) {
            try {
                mapChosen = br.readLine();

                //if user typed 'small'
                if ((mapChosen.toLowerCase(Locale.ROOT).equals("small"))) {
                    mapChosen = "small_example_map.txt";
                    defaultMap = false;
                    break;
                }
                //if user typed 'medium'
                else if ((mapChosen.toLowerCase(Locale.ROOT).equals("medium"))) {
                    mapChosen = "medium_example_map.txt";
                    defaultMap = false;
                    break;
                }
                //if user typed 'large'
                else if ((mapChosen.toLowerCase(Locale.ROOT).equals("large"))) {
                    mapChosen = "large_example_map.txt";
                    defaultMap = false;
                    break;
                }
                //if user typed invalid input, but a string
                else{
                    System.out.println("Invalid input. Default selected.");
                    defaultMap = true;
                    break;
                }
                //if string not recognised
            } catch (IOException e) {
                System.out.println("Invalid input. Default selected.");
                defaultMap = true;
                break;
            }
        }

        GameLogic game;

        //if user correctly specified their chosen map
        if (!defaultMap){
            game = new GameLogic(mapChosen);
        }

        //use the default map
        else{
            game = new GameLogic();
        }
        game.running = true;
        player1.spawnHumanPlayer(game.map1);
        bot1.spawnBotPlayer(game.map1, player1);


        while (game.gameRunning()) {

            //passing true with chooseAction means human turn, false means bot turn
            System.out.println(game.chooseAction(player1.getNextAction(player1.getInputFromConsole()), true));
            System.out.println("bot taking turn now.");
            game.chooseAction(bot1.getNextAction(bot1.generateAction()), false);

            //if human's position is the same as bot's position
            if ((player1.getPositionX() == bot1.getPositionX())
                    && (player1.getPositionY() == bot1.getPositionY())) {
                System.out.println(game.quitGame(true));
            }
        }

    }


    public static void main(String[] args) {
        runGame();
    }
}



