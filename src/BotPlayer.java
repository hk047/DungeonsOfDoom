import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class BotPlayer extends Player {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private boolean seenHumanPlayer;
    private int distanceFromHumanX;
    private int distanceFromHumanY;

    protected void spawnBotPlayer(Map currentMap, HumanPlayer humanPlayer){
        Random rand = new Random();
        int randX;
        int randY;
        do {
            //generates a random point within the boundaries of the map
            randX = rand.nextInt(currentMap.getMapWidth() - 1);
            randY = rand.nextInt(currentMap.getMapLength() - 1);
        } while (currentMap.getMap()[randY][randX] != '.' && currentMap.getMap()[randY][randX] != 'E'
                && currentMap.getMap()[randY][randX] != currentMap.getMap()[humanPlayer.getPositionY()][humanPlayer.getPositionX()]); //re-generates until chosen point is valid

        //sets the bot to the generated position
        setPositionX(randX);
        setPositionY(randY);
    }

    //logic for how the bot decides to take an action
    protected String generateAction(){

        //creates a random number
        Random rand = new Random();
        int randomNum = rand.nextInt(10);

        //if the bot has seen the player from a recent look or the random number satisfies the condition
        //random number helps the bot move if the bot is far away and won't see the player for a while
        if(seenHumanPlayer || randomNum <= 5){
            System.out.println("dfromx: " + distanceFromHumanX);
            System.out.println("dfromy: " + distanceFromHumanY);

            //if bot is on the east of the player or random number satisfies
            if(distanceFromHumanX>0 || randomNum == 1){
                seenHumanPlayer = false;
                distanceFromHumanX -= 1;
                return "move west";
            }
            //if bot is on the west of the player or random number satisfies
            else if(distanceFromHumanX<0 || randomNum == 2){
                seenHumanPlayer = false;
                distanceFromHumanX += 1;
                return "move east";
            }
            //if bot is on the south of the player or random number satisfies
            else if(distanceFromHumanY>0 || randomNum == 3){
                seenHumanPlayer = false;
                distanceFromHumanY -= 1;
                return "move north";
            }
            //if bot is on the north of the player or random number satisfies
            else if(distanceFromHumanY<0 || randomNum == 4){
                seenHumanPlayer = false;
                distanceFromHumanY += 1;
                return "move south";
            }
        }
        //if bot hasn't seen player and random number doesn't satisfy conditions
        return "look";
    }


    protected void setSeenHumanPlayer(boolean x){
        seenHumanPlayer = x;
    }

    protected int getDistanceFromHumanX(){
        return distanceFromHumanX;
    }

    protected int getDistanceFromHumanY(){
        return distanceFromHumanY;
    }

    protected void setDistanceFromHumanX(int x){
        distanceFromHumanX = x;
    }

    protected void setDistanceFromHumanY(int y){
        distanceFromHumanY = y;
    }



}