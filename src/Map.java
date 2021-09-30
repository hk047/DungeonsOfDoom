import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Reads and contains in memory the map of the game.
 *
 */
public class Map {

	/* Representation of the map */
	private char[][] map;
	
	/* Map name */
	private String mapName;
	
	/* Gold required for the human player to win */
	private int goldRequired;
	

	// Default constructor, creates the default map "Very small Labyrinth of doom".
	public Map() {
		mapName = "Very small Labyrinth of Doom";
		goldRequired = 2;
		map = new char[][]{
		{'#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#'},
		{'#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','#'},
		{'#','.','.','.','.','.','.','G','.','.','.','.','.','.','.','.','.','E','.','#'},
		{'#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','#'},
		{'#','.','.','E','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','#'},
		{'#','.','.','.','.','.','.','.','.','.','.','.','G','.','.','.','.','.','.','#'},
		{'#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','#'},
		{'#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','#'},
		{'#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#'}
		};
	}

	/**
	 * Constructor that accepts a map to read in from.
	 *
	 * @param : The filename of the map file.
	 */
	public Map(String fileName) {
		readMap(fileName);
	}



    /**
     * @return : Gold required to exit the current map.
     */
    protected int getGoldRequired() {
        return goldRequired;
    }



    /**
     * @return : The map as stored in memory.
     */
    protected char[][] getMap() {
        return map;
    }


    protected int getMapWidth(){
		return map[0].length;
	}

	protected int getMapLength(){
    	return map.length;
	}

    /**
     * @return : The name of the current map.
     */
    protected String getMapName() {
        return mapName;
    }


    

    /**
     * Reads the map from file.
     *
	 * @param : Name of the map's file.
	 * @return
	 */

    //gets the desired file from current directory and processes it into goldRequired, name and the 2D array
    protected void readMap(String fileName) {
		File file = new File(fileName);
		int totalLines = 0;
		int lineWidth = 0;
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String inputFromFile = scanner.nextLine();

				//if current line displays the name
				if (inputFromFile.startsWith("n")){
					mapName = inputFromFile.substring(5);
				}

				//if current line displays the gold required
				else if (inputFromFile.startsWith("w")){
					goldRequired = Integer.parseInt(inputFromFile.substring(4));
				}

				//if part of the actual map
				else{
					totalLines += 1;
					char[] exampleLine = inputFromFile.toCharArray();
					lineWidth = exampleLine.length;
				}
			}
			scanner.close();

		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
		}

		//create the map array
		map = new char[totalLines][lineWidth];
		int currentRow = 0;
		int currentColumn = 0;

		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {

				//read the next line from file
				String inputFromFile = scanner.nextLine();

				if (inputFromFile.startsWith("n")){
					mapName = inputFromFile.substring(5);
				}
				else if (inputFromFile.startsWith("w")){
					goldRequired = Integer.parseInt(inputFromFile.substring(4));
				}
				else{
					char[] line = inputFromFile.toCharArray();

					// for each char on the current line
					for (char c : line){
						map[currentRow][currentColumn] = c; //add current char to the map
						currentColumn+=1;
					}
					currentColumn = 0;
					currentRow += 1;
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
		}
	}


	//updates the map at a given location
	protected boolean updateMap(int y, int x, char newC){
    	map[y][x] = newC;
    	return true;
	}


}
