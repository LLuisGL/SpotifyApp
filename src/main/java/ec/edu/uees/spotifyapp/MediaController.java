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
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class MediaController implements Initializable {
    @FXML
    private MediaView mediaView;
    @FXML
    private Label nombreArtistaLabel;
    @FXML
    private ImageView iconoCancionImage;
    @FXML
    private Label nombreCancionLabel;
    @FXML
    private Slider slider;
    @FXML
    private Label maxDurationLabel;
    @FXML
    private Label actualDurationLabel;
    
    private GestorVideo gestorvideo = new GestorVideo();
    private Media media;
    private MediaPlayer mediaPlayer;
    private boolean isPaused = true;
    @FXML
    public void start(){
        if(isPaused)
        {
           mediaPlayer.play();
           isPaused = false;
        }
        else{
            mediaPlayer.pause();
            isPaused = true;
        }       
    }
    @FXML 
    public void NextVideo(){
        slider.setValue(0);
        Video nextVide;
        try {
            nextVide = gestorvideo.getNextVideo();
            String UrlString = nextVide.getURlVideo();
            String UrlMedia = Directorio()+UrlString;
            Media NextMedia = new Media(UrlMedia);
            mediaPlayer = new MediaPlayer(NextMedia);
            mediaView.setMediaPlayer(mediaPlayer);
            mediaPlayer.currentTimeProperty().addListener(((observableValue, oldValue, newValue) -> {
                if(slider.getValue() >= NextMedia.getDuration().toSeconds() - 0.5){
                    slider.setValue(0);
                    NextVideo();
                }else{
                    slider.setValue(newValue.toSeconds());
                    actualDurationLabel.setText(String.valueOf((int)slider.getValue()));
                }
            }));
            mediaPlayer.setOnReady(() -> {
                Duration totalDuration = NextMedia.getDuration();
                System.out.println(totalDuration);
                maxDurationLabel.setText(String.valueOf((int)totalDuration.toSeconds()));
                slider.setMax(totalDuration.toSeconds());
                System.out.println(slider.getValue());
            });
            String urlIcono = nextVide.getIconoVideo();
            String urlIconoMedia = Directorio()+urlIcono;
            System.out.println(urlIconoMedia);
            Image image = new Image(urlIconoMedia);
            iconoCancionImage.setImage(image);
            nombreArtistaLabel.setText(nextVide.getArtistaVideo());
            nombreCancionLabel.setText(nextVide.getNombreVideo());
            Thread.sleep(500);
            mediaPlayer.setAutoPlay(true);
        } catch (ExcepcionesVideo ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
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
            mediaPlayer.currentTimeProperty().addListener(((observableValue, oldValue, newValue) -> {
                if(slider.getValue() >= prevMedia.getDuration().toSeconds()){
                    NextVideo();
                }
                slider.setValue(newValue.toSeconds());
                actualDurationLabel.setText("" + slider.getValue());
            }));
            mediaPlayer.setOnReady(() -> {
                Duration totalDuration = prevMedia.getDuration();
                System.out.println(totalDuration);
                maxDurationLabel.setText("" + totalDuration.toSeconds());
                slider.setMax(totalDuration.toSeconds());
                System.out.println(slider.getValue());
            });
            String urlIcono = prevVideo.getIconoVideo();
            String urlIconoMedia = Directorio()+urlIcono;
            Image image = new Image(urlIconoMedia);
            iconoCancionImage.setImage(image);
            nombreArtistaLabel.setText(prevVideo.getArtistaVideo());
            nombreCancionLabel.setText(prevVideo.getNombreVideo());
            Thread.sleep(500);
            mediaPlayer.setAutoPlay(true);
        } catch (ExcepcionesVideo ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
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
    
    @FXML
    public void sliderPressed(){
        mediaPlayer.seek(Duration.seconds(slider.getValue()));
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
    }
}
