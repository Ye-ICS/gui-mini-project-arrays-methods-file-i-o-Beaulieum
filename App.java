import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
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
        HBox groot = new HBox();
        groot.setStyle("-fx-background-color: lavender;-fx-spacing: 10;");
        int[] buttonAmount = new int[6]; 
        
        Button[] lights = new Button[buttonAmount.length];
        int[] buttonValue = {0,1,1,1,1,1};
        
        for(int i = 0; i < buttonAmount.length; i++){
            Button light = new Button();
            light.setPrefSize(40, 40);
            groot.getChildren().add(light);
            light.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 5; -fx-background-radius: 6");
            lights[i] = light;
            int index = i;
            if(index == 0){
                light.setOpacity(0);
            }
            light.setOnMousePressed(event ->{
                changeButtons(lights, index, buttonValue);
            });
            }
        

        Scene scene = new Scene(groot, 480, 150);
        stage.setScene(scene);
        stage.setTitle("Lights Out");
        stage.show();

     
    }
    private void changeButtons(Button[] lights, int index, int[] buttonValue) {
            for (int k = index-1 ; k <= index+1; k++){
                if (k == 0){
                    
                }else if (buttonValue[k] == 1 && k>=0 && k<lights.length){
                lights[k].setStyle("-fx-background-color: white; -fx-text-fill: white; -fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 5; -fx-background-radius: 6");   
                buttonValue[k] = 0;
                } else if (buttonValue[k] == 0 && k>=0 && k<lights.length){
                lights[k].setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 5; -fx-background-radius: 6");   
                buttonValue[k] = 1;    
                }
        
            }
     
    }    
}
