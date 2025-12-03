import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import javafx.scene.control.Button;


public class Logic {
    static void logic(){
    
    Scanner sc = new Scanner(System.in);
    
    System.out.println("Welcome to Lights out a game where you need to turn off all the lights\n\nThe lights coresponding to the one you pick will change whem it is changed");    
    System.out.println("Would you like to play easy mode or hard mode? (Easy/Hard)");
    String response = sc.nextLine();
    int difficulty = 0;
    if (response.equalsIgnoreCase("easy")){
        difficulty = 1;
    } else if(response.equalsIgnoreCase("Hard")){
        difficulty = 2;
    } else {
        System.out.println("invalid");
    }
    int[][] lightAmount = new int[5][5]; // 5x5 grid for lights
    int[][] lightValue = new int[lightAmount.length][lightAmount.length]; // value of each light "on, off..."
    
        for(int i=0; i < lightAmount[0].length; i++){ // generates grid with random values
            for(int k=0; k < lightAmount[0].length; k++){

                lightValue[i][k] = ThreadLocalRandom.current().nextInt(0,difficulty+1);
                System.out.print(lightValue[i][k] + " ");               
            }
            System.out.println("\n");
        }
        boolean gameOn = true;
        checkIfComplete(lightValue); 
        while(gameOn){
        
            int[] guess = new int[2]; 
        System.out.print("choose button: 'y x': "); 
        guess[0] = sc.nextInt();
        guess[1] = sc.nextInt();
        if(guess[0] < 0 || guess[1] < 0|| guess[0] > lightAmount.length || guess[0] > lightAmount.length){
            System.out.println("Invalid guess");
            System.exit(0);
        }
        
        onInput(lightValue, difficulty, guess);
        updatedGame(lightValue);
        checkIfComplete(lightValue);
        }
    }
    /**
     * Changes appearence of buttons deppending on specific input
     * @param lightValue
     * @param difficulty
     * @param guess
     */
    static void onInput(int[][] lightValue, int difficulty, int[] guess){

        int indexY = guess[0]-1;
        int indexX = guess[1]-1;
            
        
        lightValue[indexY][indexX] = (lightValue[indexY][indexX] + 1) % (difficulty+1); 
        if(indexY < 4){ 
        lightValue[indexY+1][indexX] = (lightValue[indexY+1][indexX] + 1) % (difficulty+1);
        } 
        if(indexY > 0){
        lightValue[indexY-1][indexX] = (lightValue[indexY-1][indexX] + 1) % (difficulty+1);
        }
        if(indexX < 4){
        lightValue[indexY][indexX+1] = (lightValue[indexY][indexX+1] + 1) % (difficulty+1);
        }
        if(indexX > 0){
        lightValue[indexY][indexX-1] = (lightValue[indexY][indexX-1] + 1) % (difficulty+1);
        }
    }
    /**
     * Updates map after each alteration
     * @param lightValue
     */
    static void updatedGame(int[][] lightValue){
        for(int i=0; i < lightValue[0].length; i++){
            for(int k=0; k < lightValue[0].length; k++){
                System.out.print(lightValue[i][k] + " ");
            }
            System.out.println("\n");
        }
    }

    static boolean checkIfComplete(int[][] lightValue){
       boolean gameOn;
        if(Arrays.asList(lightValue).contains(1)){
        gameOn = true;
       } else if(Arrays.asList(lightValue).contains(2)){
        gameOn = true;
       } else {
        gameOn = false;
       }
       return gameOn;
    }
    
    static int[][] randomizeLights(Button[][] lightAmount, int difficulty){
    int[][] lightValue = new int[lightAmount.length][lightAmount.length]; // value of each light "on, off..."     
    for(int i=0; i < lightAmount[0].length; i++){ // generates grid with random values    
        for(int k=0; k < lightAmount[0].length; k++){

                lightValue[i][k] = ThreadLocalRandom.current().nextInt(0,difficulty+1);               
            }
        }
        return lightValue;
    }


}


