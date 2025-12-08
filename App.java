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
import javafx.util.Duration;

import java.io.FileNotFoundException;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

/**
 * Main class for Lights out.
 */
public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        // Create components to add.
        VBox groot = new VBox();
        StackPane gameScreen = new StackPane();
        GridPane lightHolder = new GridPane();
        Label endLabel = new Label("Congratulations,\n        you won.");
        endLabel.setPrefSize(300, 200);
        endLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        endLabel.setAlignment(Pos.CENTER);
        Label startScreen = new Label("Lights out");
        startScreen.setPrefSize(300, 200);
        startScreen.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        startScreen.setAlignment(Pos.CENTER);
        endLabel.setOpacity(0);
        groot.setStyle("-fx-background-color: lavender;-fx-spacing: 10;");
        int difficulty = 1;
        Button[][] buttonAmount = new Button[6][6];
        int[][] buttonValue = Logic.randomizeLights(buttonAmount, difficulty);

        for (int k = 0; k < buttonAmount.length; k++) {
            for (int i = 0; i < buttonAmount.length; i++) {
                Button light = new Button();
                light.setPrefSize(40, 40);
                light.setOpacity(0.5);
                lightHolder.add(light, k, i);
                buttonAmount[k][i] = light;

                int[] buttonIndex = new int[2];

                buttonIndex[0] = k;
                buttonIndex[1] = i;

                light.setOnMousePressed(event -> {
                    startScreen.setOpacity(0);
                    long startTime = System.currentTimeMillis();
            
                    Logic.onInput(buttonValue, difficulty, buttonIndex);
                    changeButtons(buttonAmount, buttonIndex, buttonValue);

                    boolean gameOn = Logic.checkIfComplete(buttonValue);
                    if (gameOn == false) {
                        endLabel.setOpacity(1);
                        long endTime = System.currentTimeMillis();
                        try{
                        Logic.readAndUpdateHighscore(startTime,endTime);
                        } catch (FileNotFoundException fnfe){

                        }
                    }
                });
            }
        }
        gameScreen.getChildren().addAll(endLabel, startScreen, lightHolder);
        Button easyDifficuly = new Button();
        easyDifficuly.setOnMousePressed(event -> {
            // difficulty = 1;
        });
        Button hardDifficuly = new Button();
        hardDifficuly.setOnMousePressed(event -> {
            // difficulty = 2;
        });
        groot.getChildren().add(gameScreen);

        Scene scene = new Scene(groot, 240, 240);
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
                } else if (buttonValue[k][i] == 1) {
                    buttonAmount[k][i]
                            .setStyle("-fx-background-color: garnet;-fx-border-color: black;-fx-border-width: 1;");
                }

            }

        }
    }
}
