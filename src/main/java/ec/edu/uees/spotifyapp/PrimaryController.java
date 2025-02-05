package ec.edu.uees.spotifyapp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class PrimaryController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Thread.sleep(1500);
            App.setRoot("login");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
}
