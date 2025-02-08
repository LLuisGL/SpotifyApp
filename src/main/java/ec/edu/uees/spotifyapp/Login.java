package ec.edu.uees.spotifyapp;

import java.io.IOException;
import javafx.fxml.FXML;

public class Login {
    @FXML 
    public void Register(){
        
    }
    
    
    @FXML
    public void LogIn(){
        try {
            
            App.setRoot("main");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
