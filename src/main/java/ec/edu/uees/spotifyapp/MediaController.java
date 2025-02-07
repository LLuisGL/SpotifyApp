package ec.edu.uees.spotifyapp;

import Video.ExcepcionesVideo;
import Video.Video;
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
import Video.GestorVideo;

public class MediaController  {
    @FXML
    private MediaView mediaView;
    private GestorVideo gestorvideo = new GestorVideo();
    private Media media;
    private MediaPlayer mediaPlayer;
    
    @FXML
    public void selectMedia(){
            String url = Directorio()+"/media/song1.mp4";
            System.out.println(url);
            media = new Media(url);
            mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);
            mediaPlayer.setAutoPlay(true);
                
    }
    
    @FXML 
    public void NextVideo(){
        Video nextVide;
        try {
            nextVide = gestorvideo.getNextVideo();
            String UrlString = nextVide.getURlVideo();
            String UrlMedia = Directorio()+UrlString;
            Media NextMedia = new Media(UrlMedia);
            mediaPlayer = new MediaPlayer(NextMedia);
            mediaView.setMediaPlayer(mediaPlayer);
            mediaPlayer.setAutoPlay(true);
        } catch (ExcepcionesVideo ex) {
            ex.printStackTrace();
        }
        
    }
    @FXML 
    public void PrevVideo(){
        Video prevVideo;
        try{
            prevVideo = gestorvideo.getPrevVideo();
            String UrlString = prevVideo.getURlVideo();
            String UrlMedia = Directorio()+UrlString;
            Media prevMedia = new Media(UrlMedia);
            mediaPlayer = new MediaPlayer(prevMedia);
            mediaView.setMediaPlayer(mediaPlayer);
            mediaPlayer.setAutoPlay(true);
        } catch (ExcepcionesVideo ex) {
            ex.printStackTrace();
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
    
    private String Directorio(){
        String userDir = System.getProperty("user.dir");
        userDir = userDir.replace("\\", "/");
        File archivo = new File(userDir);
        String newURI = archivo.toURI().toString();
     return newURI;   
    }
}
