import java.util.Locale;

public class Player {


    private int positionX;
    private int positionY;


    protected int getPositionX(){
        return positionX;
    }

    protected int getPositionY(){
        return positionY;
    }

    protected void setPositionX(int x){
        positionX = x;
    }

    protected void setPositionY(int y){
        positionY = y;
    }


    /**
     * Processes the command. It should return a reply in form of a String, as the protocol dictates.
     * Otherwise it should return the string "Invalid".
     *
     * @return : Processed output or Invalid if the @param command is wrong.
     */
    protected String getNextAction(String nextAction) {


        if (nextAction.equals("hello")) {
            return nextAction;
        }
        else if (nextAction.equals("gold")) {
            return nextAction;
        }
        else if (nextAction.equals("pickup")) {
            return nextAction;
        }
        else if (nextAction.equals("look")) {
            return nextAction;
        }
        else if (nextAction.equals("quit")) {
            return nextAction;
        }
        else{
            if (nextAction.startsWith("move")) {
                if (nextAction.substring(4).equals(" north") || nextAction.substring(4).equals(" east") ||
                        nextAction.substring(4).equals(" south") || nextAction.substring(4).equals(" west")) {
                    return nextAction;
                } else {
                    return "invalid";
                }
            }
            else{
                return "invalid";
            }
        }
    }
}

