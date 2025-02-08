package ec.edu.uees.spotifyapp;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main {
    
    private double x = 0;
    private double y = 0;
    
    @FXML
    private StackPane principalStackPane;
    
    @FXML
    public void dragWindow(MouseEvent event){
        Stage stage = (Stage) principalStackPane.getScene().getWindow();
        stage.setOpacity(0.8);
        stage.setX(event.getScreenX() - x/4);
        stage.setY(event.getScreenY() - y);
    }
    
    @FXML
    public void undragWindow(){
        Stage stage = (Stage) principalStackPane.getScene().getWindow();
        stage.setOpacity(1);
    }
    
    @FXML
    public void windowPressed(MouseEvent event){
        x = event.getScreenX();
        y = event.getScreenY();
    }
    
    @FXML
    public void SwitchToLogIn(){
        try {
            App.setRoot("login");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @FXML
    public void SwitchToMusic(){
        try {
            App.setRoot("playing_music");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
