import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.text.Font;

/**
 * Template JavaFX application.
 */
public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) {
        // Create components to add.
        VBox groot = new VBox();
        GridPane lightHolder = new GridPane();
        groot.setStyle("-fx-background-color: lavender;-fx-spacing: 10;"); 
        int difficulty = 1;
        
        Button[][] buttonAmount = new Button[5][5];
        int[] buttonValue = new int[buttonAmount.length-1];
        Logic.randomizeLights(buttonAmount, difficulty); 
        
        for(int k = 0; k < buttonAmount.length; k++){
        for(int i = 0; i < buttonAmount.length; i++){
            Button light = new Button();
            light.setPrefSize(40, 40);
            lightHolder.add(light,k,i);
            buttonAmount[k][i] = light;
            int index = i;
                light.setOnMousePressed(event ->{
                // changeButtons(buttonAmount, index, buttonValue);
            });
            


        
            }
        }
        
        Button easyDifficuly = new Button();
        // easyDifficuly.setOnMousePressed(event ->{
        //         difficulty = 1;
        // });
        groot.getChildren().add(lightHolder);
        

        Scene scene = new Scene(groot, 480, 150);
        stage.setScene(scene);
        stage.setTitle("Lights Out");
        stage.show();

     
    }
    /**
     * Changes looks and button value for light buttons around specific index
     * @param lights
     * @param index
     * @param buttonValue
     */
    private void changeButtons(Button[][] buttonAmount, int index, int[][] buttonValue) {
    

     
    } 
    private void checkAnswer(int[] buttonValue){
        for(int i = 0;i< buttonValue.length; i++){
            
        }
    }   
}
