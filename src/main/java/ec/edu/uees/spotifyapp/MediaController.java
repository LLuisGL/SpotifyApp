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
import Video.GestorVideo;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
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
    
    
    private double x = 0;
    private double y = 0;
    private double volumen = 1;
    
    @FXML
    private StackPane principalStackPane;
    
    @FXML
    public void subirVolumen(){
        if(volumen < 1){
            volumen = volumen + 0.1;
            mediaPlayer.setVolume(volumen);
        }
    }
    
    @FXML
    public void bajarVolumen(){
        if(volumen > 0){
            volumen = volumen - 0.1;
            mediaPlayer.setVolume(volumen);
        }
    }
    
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
    public void setUpSlider(){
        
    }
    
    @FXML
    public void IniciarVideo(){
        Video v1 = gestorvideo.getFirstVideo();
        String url = v1.getURlVideo();
        String UrlString = Directorio()+url;
        System.out.println(UrlString);
        try{
            Media NextMedia = new Media(UrlString);
                mediaPlayer = new MediaPlayer(NextMedia);
                mediaPlayer.setVolume(volumen);
                mediaView.setMediaPlayer(mediaPlayer);
                mediaPlayer.currentTimeProperty().addListener(((observableValue, oldValue, newValue) -> {
                    double totalDuration = NextMedia.getDuration().toSeconds();
                    double actualDuration = newValue.toSeconds();
                    double actualPercent = (100*actualDuration)/totalDuration;
                    String cssProperty = "-fx-background-color: linear-gradient(to right, #" + v1.getColorVideo() + " " + String.format("%.0f", actualPercent) + "%, #969696 " + String.format("%.0f", actualPercent) + "%);";
                    slider.lookup(".track").setStyle(cssProperty);
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
                String urlIcono = v1.getIconoVideo();
                String urlIconoMedia = Directorio()+urlIcono;
                System.out.println(urlIconoMedia);
                Image image = new Image(urlIconoMedia);
                iconoCancionImage.setImage(image);
                nombreArtistaLabel.setText(v1.getArtistaVideo());
                nombreCancionLabel.setText(v1.getNombreVideo());
                Thread.sleep(500);
                mediaPlayer.setAutoPlay(true);
      
        } catch (InterruptedException ex) {
                ex.printStackTrace();
            }    
    }
    @FXML 
    public void NextVideo(){
            mediaPlayer.stop();
            slider.setValue(0);
            Video nextVide;
            try {
                nextVide = gestorvideo.getNextVideo();
                String UrlString = nextVide.getURlVideo();
                String UrlMedia = Directorio()+UrlString;
                Media NextMedia = new Media(UrlMedia);
                mediaPlayer = new MediaPlayer(NextMedia);
                mediaPlayer.setVolume(volumen);
                mediaView.setMediaPlayer(mediaPlayer);
                mediaPlayer.currentTimeProperty().addListener(((observableValue, oldValue, newValue) -> {
                    double totalDuration = NextMedia.getDuration().toSeconds();
                    double actualDuration = newValue.toSeconds();
                    double actualPercent = (100*actualDuration)/totalDuration;
                    String cssProperty = "-fx-background-color: linear-gradient(to right, #" + nextVide.getColorVideo() + " " + String.format("%.0f", actualPercent) + "%, #969696 " + String.format("%.0f", actualPercent) + "%);";
                    slider.lookup(".track").setStyle(cssProperty);
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
        mediaPlayer.stop();
        slider.setValue(0);
        Video prevVideo;
        try{
            prevVideo = gestorvideo.getPrevVideo();
            String UrlString = prevVideo.getURlVideo();
            String UrlMedia = Directorio()+UrlString;
            Media prevMedia = new Media(UrlMedia);
            mediaPlayer = new MediaPlayer(prevMedia);
            mediaPlayer.setVolume(volumen);
            mediaView.setMediaPlayer(mediaPlayer);
            mediaPlayer.currentTimeProperty().addListener(((observableValue, oldValue, newValue) -> {
                double totalDuration = prevMedia.getDuration().toSeconds();
                double actualDuration = newValue.toSeconds();
                double actualPercent = (100*actualDuration)/totalDuration;
                String cssProperty = "-fx-background-color: linear-gradient(to right, #" + prevVideo.getColorVideo() + " " + String.format("%.0f", actualPercent) + "%, #969696 " + String.format("%.0f", actualPercent) + "%);";
                System.out.println(cssProperty);
                slider.lookup(".track").setStyle(cssProperty);
                if(slider.getValue() >= prevMedia.getDuration().toSeconds()- 0.5){
                    slider.setValue(0);
                    NextVideo();
                }
                slider.setValue(newValue.toSeconds());
                actualDurationLabel.setText(String.valueOf((int)slider.getValue()));
            }));
            mediaPlayer.setOnReady(() -> {
                Duration totalDuration = prevMedia.getDuration();
                System.out.println(totalDuration);
                maxDurationLabel.setText(String.valueOf((int)totalDuration.toSeconds()));
                slider.setMax((int)totalDuration.toSeconds());
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
            mediaPlayer.stop();
            mediaPlayer = null;
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
        IniciarVideo();
    }
}
