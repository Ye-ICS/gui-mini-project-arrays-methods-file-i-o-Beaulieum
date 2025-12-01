import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Logic {
    public static void main(String[] args) {
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
    int[][] lightAmount = new int[5][5];
    int[][] lightValue = new int[lightAmount.length][lightAmount.length];
    
        for(int i=0; i < lightAmount[0].length; i++){
            for(int k=0; k < lightAmount[0].length; k++){

                lightValue[i][k] = ThreadLocalRandom.current().nextInt(0,difficulty+1);
                System.out.print(lightValue[i][k]);               
            }
            System.out.println();
        }
        boolean gameOn = true;
         while(gameOn){
        
            int[] guess = new int[2]; 
        System.out.println("Take a guess: (y x)"); 
        guess[0] = sc.nextInt();
        guess[1] = sc.nextInt();
        

        
        
        
        onInput(lightValue, difficulty, guess);
        updatedGame(lightValue);
        }
    }
    static void onInput(int[][] lightValue, int difficulty, int[] guess){

        int indexY = guess[0];
        int indexX = guess[1];
            
        lightValue[indexY][indexX] += 1 % (difficulty+1); 
        lightValue[indexY+1][indexX] += 1 % (difficulty+1); 
        lightValue[indexY-1][indexX] += 1 % (difficulty+1);
        lightValue[indexY][indexX+1] += 1 % (difficulty+1);
        lightValue[indexY][indexX-1] += 1 % (difficulty+1);

    }

    static void updatedGame(int[][] lightValue){
        for(int i=0; i < lightValue[0].length; i++){
            for(int k=0; k < lightValue[0].length; k++){
                System.out.print(lightValue[i][k]);
            }
            System.out.println();
        }
    }

}


