import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Random;

/**
 * Runs the game with a human player and contains code needed to read inputs.
 *
 */
public class HumanPlayer extends Player{

    private int gold;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


    /**
     * Reads player's input from the console.
     * <p>
     * return : A string containing the input the player entered.
     */

    protected String getInputFromConsole(){

        String userInput = "";
        System.out.print(String.format("player 1's turn: "));

        //takes input from the user
        try {
            userInput = br.readLine();
        } catch (IOException e) {
            return "Input given was not a string."; //returned if the input wasn't suitable
        }
        return userInput;
    }


    //randomly locates the  player
    protected void spawnHumanPlayer(Map currentMap){
        Random rand = new Random();
        int randX;
        int randY;
        do {
            //generates a random point within the boundaries of the map
            randX = rand.nextInt(currentMap.getMapWidth() - 1);
            randY = rand.nextInt(currentMap.getMapLength() - 1);
        } while (currentMap.getMap()[randY][randX] != '.' && currentMap.getMap()[randY][randX] != 'E'); //re-generates until chosen point is valid

        //sets the player to the generated position
        setPositionX(randX);
        setPositionY(randY);
    }



    protected int getGold(){
        return gold;
    }

    protected void setGold(int goldIncrease){
        int currentGold = getGold();
        currentGold += goldIncrease;
        gold = currentGold;
    }







}