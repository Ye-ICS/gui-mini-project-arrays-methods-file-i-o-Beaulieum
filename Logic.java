import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import javafx.scene.control.Button;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Logic {

    /**
     * Changes appearence of buttons deppending on specific input
     * 
     * @param lightValue
     * @param difficulty
     * @param guess
     */
    static void onInput(int[][] lightValue, int difficulty, int[] guess) {

        int indexY = guess[0];
        int indexX = guess[1];

        lightValue[indexY][indexX] = (lightValue[indexY][indexX] + 1) % (difficulty + 1);
        if (indexY < 5) {
            lightValue[indexY + 1][indexX] = (lightValue[indexY + 1][indexX] + 1) % (difficulty + 1);
        }
        if (indexY > 0) {
            lightValue[indexY - 1][indexX] = (lightValue[indexY - 1][indexX] + 1) % (difficulty + 1);
        }
        if (indexX < 5) {
            lightValue[indexY][indexX + 1] = (lightValue[indexY][indexX + 1] + 1) % (difficulty + 1);
        }
        if (indexX > 0) {
            lightValue[indexY][indexX - 1] = (lightValue[indexY][indexX - 1] + 1) % (difficulty + 1);
        }
    }

    /**
     * Updates map after each alteration
     * 
     * @param lightValue
     */
    static void updatedGame(int[][] lightValue) {
        for (int i = 0; i < lightValue[0].length; i++) {
            for (int k = 0; k < lightValue[0].length; k++) {
                System.out.print(lightValue[i][k] + " ");
            }
            System.out.println("\n");
        }
    }

    /**
     * CHecks if game is complete, returns boolean to stop game.
     * 
     * @param buttonValue
     * @return
     */
    static boolean checkIfComplete(int[][] buttonValue) {
        boolean gameOn = true;

        for (int i = 0; i < buttonValue[0].length; i++) {
            for (int k = 0; k < buttonValue[0].length; k++) {
                if (buttonValue[i][k] == 1) {
                    gameOn = false;
                }
            }
        }
        return gameOn;
    }

    /**
     * Randomizes the value for each light
     * 
     * @param lightAmount
     * @param difficulty
     * @return
     */
    static int[][] randomizeLights(Button[][] lightAmount, int difficulty) {
        int[][] lightValue = new int[lightAmount.length][lightAmount.length]; // value of each light "on, off..."
        for (int i = 0; i < lightAmount[0].length; i++) { // generates grid with random values
            for (int k = 0; k < lightAmount[0].length; k++) {

                lightValue[i][k] = ThreadLocalRandom.current().nextInt(0, difficulty + 1);
            }
        }
        return lightValue;
    }

    /**
     * calculates the time it takes to finish the game and puts it into a file
     * 
     * @param startTime
     * @param endTime
     * @throws FileNotFoundException
     */
    static void readAndUpdateHighscore(long startTime, long endTime) throws FileNotFoundException {
        long finalTime = (endTime - startTime) / 1000;
        int newFinal = Math.round(finalTime);
        File highScore = new File("HighScore.txt");
        
        try {
            FileWriter addHighScore = new FileWriter("HighScore.txt");
            addHighScore.write(newFinal + " Seconds");
            addHighScore.close();
        } catch (IOException ioe) {

        }
        Scanner fs = new Scanner(highScore);
        while (fs.hasNext()) {
            String score = fs.nextLine();
        }

    }

}
