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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MediaController implements Initializable {
    @FXML
    private MediaView mediaView;
    @FXML
    private Label nombreArtistaLabel;
    @FXML
    private ImageView iconoCancionImage;
    @FXML
    private Label nombreCancionLabel;
    
    private GestorVideo gestorvideo = new GestorVideo();
    private Media media;
    private MediaPlayer mediaPlayer;
    private boolean isPaused = false;
    @FXML
    public void start(){
     if(mediaPlayer != null){
        if(isPaused)
        {
           mediaPlayer.play();
           isPaused = false;
        }
        else{
            mediaPlayer.stop();
        }
      }       
    }
    @FXML
    public void pause(){
        if(mediaPlayer != null){
            mediaPlayer.pause();
            isPaused = true;
        }
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
            String urlIcono = nextVide.getIconoVideo();
            String urlIconoMedia = Directorio()+urlIcono;
            System.out.println(urlIconoMedia);
            Image image = new Image(urlIconoMedia);
            iconoCancionImage.setImage(image);
            nombreArtistaLabel.setText(nextVide.getArtistaVideo());
            nombreCancionLabel.setText(nextVide.getNombreVideo());
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
            String urlIcono = prevVideo.getIconoVideo();
            String urlIconoMedia = Directorio()+urlIcono;
            Image image = new Image(urlIconoMedia);
            iconoCancionImage.setImage(image);
            nombreArtistaLabel.setText(prevVideo.getArtistaVideo());
            nombreCancionLabel.setText(prevVideo.getNombreVideo());
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        NextVideo();
        PrevVideo();
    }
}
