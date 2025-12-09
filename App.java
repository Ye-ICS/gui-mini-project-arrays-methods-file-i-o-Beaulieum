
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Main class for Lights out.
 */
public class App extends Application { 
    private long startTime = 0;
    public static void main(String[] args) {
        launch(args);
        
    }

    @Override
    public void start(Stage stage) throws FileNotFoundException { 
        // Create components to add.
        VBox groot = new VBox(); // main component
        groot.setStyle("-fx-background-color: lavender;");
        
        StackPane gameScreen = new StackPane();

        GridPane lightHolder = new GridPane();

        Label endLabel = new Label(); // label for the end of the game tells user score
        endLabel.setPrefSize(300, 200);
        endLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        endLabel.setAlignment(Pos.CENTER);
        endLabel.setOpacity(0);

        Label startScreen = new Label("Lights out"); // title screen is behind the grid
        startScreen.setPrefSize(300, 200);
        startScreen.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        startScreen.setAlignment(Pos.CENTER);

        Label highScore = new Label(); // highscore updates after new highscore and game is closed
        highScore.setAlignment(Pos.CENTER);
        File score = new File("HighScore.txt");
        Scanner fs = new Scanner(score);
        int currentHS = 0;
        if (fs.hasNextInt()) {
            currentHS = fs.nextInt();
        }
        int currentHSMinutes = currentHS / 60;
        int currentHSSeconds = currentHS % 60;
        highScore.setText("HighScore: " + currentHSMinutes + ":" + currentHSSeconds + " Minutes");

        int difficulty = 1;

        Button[][] buttonAmount = new Button[6][6];
        int[][] buttonValue = Logic.randomizeLights(buttonAmount, difficulty); // value of each button in a 2d button array
        
        for (int k = 0; k < buttonAmount.length; k++) { // creates a 6x6 button grid
            for (int i = 0; i < buttonAmount.length; i++) {
                Button light = new Button();
                light.setPrefSize(40, 40);
                light.setOpacity(0.5);
                lightHolder.add(light, k, i);
                buttonAmount[k][i] = light;

                int[] buttonIndex = new int[2];

                buttonIndex[0] = k;
                buttonIndex[1] = i;
                
                light.setOnMousePressed(event -> { // apon a button pressed initializes game and updates buttons
                    startScreen.setOpacity(0);
                    
                    if (startTime == 0){
                        startTime = System.currentTimeMillis();
                    }
            
                    Logic.onInput(buttonValue, difficulty, buttonIndex);
                    changeButtons(buttonAmount, buttonIndex, buttonValue);

                    boolean gameOn = Logic.checkIfComplete(buttonValue);
                    if (gameOn) {
                        long endTime = System.currentTimeMillis();
                        String actualTime = Logic.returnHighscore(startTime, endTime);
                        endLabel.setText("Congratulations,\n        you won with a time of \n " + actualTime + " Minutes\n\n\n\n\n you may now close the game.");
                        endLabel.setOpacity(1);
                        try{
                        Logic.readAndUpdateHighscore(startTime,endTime);
                        } catch (FileNotFoundException fnfe){

                        }
                    }
                });
            }
        }
        gameScreen.getChildren().addAll(endLabel, startScreen, lightHolder);
        groot.getChildren().addAll(highScore, gameScreen);

        Scene scene = new Scene(groot, 240, 280);
        stage.setScene(scene);
        stage.setTitle("Lights Out");
        stage.show();

    }

    /**
     * Changes looks and button value for light buttons around specific index
     * 
     * @param lights
     * @param index
     * @param buttonValue
     */
    private void changeButtons(Button[][] buttonAmount, int[] index, int[][] buttonValue) {
        for (int k = 0; k < buttonAmount.length; k++) {
            for (int i = 0; i < buttonAmount.length; i++) {
                if (buttonValue[k][i] == 0) {
                    buttonAmount[k][i]
                            .setStyle("-fx-background-color: white;-fx-border-color: black;-fx-border-width: 1;");
                } else if (buttonValue[k][i] == 1) {
                    buttonAmount[k][i]
                            .setStyle("-fx-background-color: red;-fx-border-color: black;-fx-border-width: 1;");
                } 

            }

        }
    }
}