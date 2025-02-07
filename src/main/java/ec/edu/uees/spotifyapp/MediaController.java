package ec.edu.uees.spotifyapp;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;

public class MediaController  {
    @FXML
    private MediaView mediaView;
    
    private Media media;
    private MediaPlayer mediaPlayer;
    
    @FXML
    public void selectMedia(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Media");
        File selectedFile = fileChooser.showOpenDialog(null);
        if(selectedFile != null){
            String url = selectedFile.toURI().toString();
            System.out.println(url);
            media = new Media(url);
            mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);
            mediaPlayer.setAutoPlay(true);
        }
    }
    
    @FXML
    public void switchToMain(){
        try {
            App.setRoot("main");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
