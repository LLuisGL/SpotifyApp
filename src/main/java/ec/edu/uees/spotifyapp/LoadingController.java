package ec.edu.uees.spotifyapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class LoadingController implements Initializable{
    
    @FXML
    private ImageView loadingImage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LoadingThread t1 = new LoadingThread();
        t1.start();
    }
    
}
