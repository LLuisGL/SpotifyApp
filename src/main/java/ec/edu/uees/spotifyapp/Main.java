package ec.edu.uees.spotifyapp;

import java.io.IOException;
import javafx.fxml.FXML;

public class Main {
    
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
            App.setRoot("login");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
