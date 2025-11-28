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

        

    
    }
}


