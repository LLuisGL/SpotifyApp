package ec.edu.uees.spotifyapp;

import Video.ExcepcionesVideo;
import Video.Video;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import Video.GestorVideo;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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
    private ImageView botonPlayStop;
    @FXML
    private ImageView subirVolumen;
    @FXML
    private ImageView bajarVolumen;
    @FXML
    private Slider slider;
    @FXML
    private Label maxDurationLabel;
    @FXML
    private Label actualDurationLabel;
    @FXML
    private VBox turnoVBox;
    @FXML
    private Label labelTurnoNuevo;
    @FXML
    private Button sigTurnoBtn;
    @FXML
    private MediaView turnAlert;
    ThreadTurno tt;
    
    private GestorVideo gestorvideo = new GestorVideo();
    private Video video;
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
            String urlImageSubir = Directorio()+"/src/main/resources/ec/edu/uees/spotifyapp/main/agregarPush.png";
            Image subirPush = new Image(urlImageSubir);
            subirVolumen.setImage(subirPush);
            mediaPlayer.setVolume(volumen);
        }
    }
    @FXML
    public void restIcoSubVolu(){
        String urlImageSu = Directorio()+"/src/main/resources/ec/edu/uees/spotifyapp/main/agregar.png";
        Image subir = new Image(urlImageSu);
        subirVolumen.setImage(subir);
    }    
    
    
    @FXML
    public void bajarVolumen(){
        if(volumen > 0){
            volumen = volumen - 0.1;
            String urlImageBajar = Directorio()+"/src/main/resources/ec/edu/uees/spotifyapp/main/menosPush.png";
            Image bajarPush = new Image(urlImageBajar);
            bajarVolumen.setImage(bajarPush);
            mediaPlayer.setVolume(volumen);
        }
    }
    @FXML
    public void restIcoBajVolu(){
        String urlImageBa = Directorio()+"/src/main/resources/ec/edu/uees/spotifyapp/main/menos.png";
        Image bajar = new Image(urlImageBa);
        bajarVolumen.setImage(bajar);
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
           String urlImagePL = Directorio()+"/src/main/resources/ec/edu/uees/spotifyapp/main/pause_1.png"; 
           Image imagePause = new Image(urlImagePL); 
           botonPlayStop.setImage(imagePause);
           isPaused = false;
        }
        else{
            mediaPlayer.pause();
            String urlImagePS = Directorio()+"/src/main/resources/ec/edu/uees/spotifyapp/main/play_3.png";
            Image imagePause = new Image(urlImagePS); 
            botonPlayStop.setImage(imagePause);
            isPaused = true;
        }       
    }
    
    
    @FXML
    public void setUpSlider(){
        mediaPlayer.stop();
        mediaPlayer.seek(Duration.seconds(slider.getValue()));
        double totalDuration = media.getDuration().toSeconds();
        double actualDuration = slider.getValue();
        double actualPercent = (100*actualDuration)/totalDuration;
        String cssProperty = "-fx-background-color: linear-gradient(to right, #" + video.getColorVideo() + " " + String.format("%.0f", actualPercent) + "%, #969696 " + String.format("%.0f", actualPercent) + "%);";
        slider.lookup(".track").setStyle(cssProperty);
        mediaPlayer.pause();   
        isPaused = true;
    }
    
    @FXML
    public void IniciarVideo(){
        video = gestorvideo.getFirstVideo();
        String url = video.getURlVideo();
        String UrlString = Directorio()+url;
        System.out.println(UrlString);
        try{
            media = new Media(UrlString);
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setVolume(volumen);
            mediaView.setMediaPlayer(mediaPlayer);
            mediaPlayer.currentTimeProperty().addListener(((observableValue, oldValue, newValue) -> {
                double totalDuration = media.getDuration().toSeconds();
                double actualDuration = newValue.toSeconds();
                double actualPercent = (100*actualDuration)/totalDuration;
                String cssProperty = "-fx-background-color: linear-gradient(to right, #" + video.getColorVideo() + " " + String.format("%.0f", actualPercent) + "%, #969696 " + String.format("%.0f", actualPercent) + "%);";
                slider.lookup(".track").setStyle(cssProperty);
                if(slider.getValue() >= media.getDuration().toSeconds() - 0.5){
                    slider.setValue(0);
                    NextVideo();
                }else{
                    slider.setValue(newValue.toSeconds());
                    actualDurationLabel.setText(String.valueOf((int)slider.getValue()));
                }
            }));
            mediaPlayer.setOnReady(() -> {
                Duration totalDuration = media.getDuration();
                System.out.println(totalDuration);
                maxDurationLabel.setText(String.valueOf((int)totalDuration.toSeconds()));
                slider.setMax(totalDuration.toSeconds());
                System.out.println(slider.getValue());
            });
            String urlIcono = video.getIconoVideo();
            String urlIconoMedia = Directorio()+urlIcono;
            System.out.println(urlIconoMedia);
            Image image = new Image(urlIconoMedia);
            iconoCancionImage.setImage(image);
            nombreArtistaLabel.setText(video.getArtistaVideo());
            nombreCancionLabel.setText(video.getNombreVideo());
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
            try {
                video = gestorvideo.getNextVideo();
                String UrlString = video.getURlVideo();
                String UrlMedia = Directorio()+UrlString;
                media = new Media(UrlMedia);
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setVolume(volumen);
                mediaView.setMediaPlayer(mediaPlayer);
                mediaPlayer.currentTimeProperty().addListener(((observableValue, oldValue, newValue) -> {
                    double totalDuration = media.getDuration().toSeconds();
                    double actualDuration = newValue.toSeconds();
                    double actualPercent = (100*actualDuration)/totalDuration;
                    String cssProperty = "-fx-background-color: linear-gradient(to right, #" + video.getColorVideo() + " " + String.format("%.0f", actualPercent) + "%, #969696 " + String.format("%.0f", actualPercent) + "%);";
                    slider.lookup(".track").setStyle(cssProperty);
                    if(slider.getValue() >= media.getDuration().toSeconds() - 0.5){
                        slider.setValue(0);
                        NextVideo();
                    }else{
                        slider.setValue(newValue.toSeconds());
                        actualDurationLabel.setText(String.valueOf((int)slider.getValue()));
                    }
                }));
                mediaPlayer.setOnReady(() -> {
                    Duration totalDuration = media.getDuration();
                    System.out.println(totalDuration);
                    maxDurationLabel.setText(String.valueOf((int)totalDuration.toSeconds()));
                    slider.setMax(totalDuration.toSeconds());
                    System.out.println(slider.getValue());
                });
                String urlIcono = video.getIconoVideo();
                String urlIconoMedia = Directorio()+urlIcono;
                System.out.println(urlIconoMedia);
                Image image = new Image(urlIconoMedia);
                iconoCancionImage.setImage(image);
                nombreArtistaLabel.setText(video.getArtistaVideo());
                nombreCancionLabel.setText(video.getNombreVideo());
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
        try{
            video = gestorvideo.getPrevVideo();
            String UrlString = video.getURlVideo();
            String UrlMedia = Directorio()+UrlString;
            media = new Media(UrlMedia);
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setVolume(volumen);
            mediaView.setMediaPlayer(mediaPlayer);
            mediaPlayer.currentTimeProperty().addListener(((observableValue, oldValue, newValue) -> {
                double totalDuration = media.getDuration().toSeconds();
                double actualDuration = newValue.toSeconds();
                double actualPercent = (100*actualDuration)/totalDuration;
                String cssProperty = "-fx-background-color: linear-gradient(to right, #" + video.getColorVideo() + " " + String.format("%.0f", actualPercent) + "%, #969696 " + String.format("%.0f", actualPercent) + "%);";
                slider.lookup(".track").setStyle(cssProperty);
                if(slider.getValue() >= media.getDuration().toSeconds()- 0.5){
                    slider.setValue(0);
                    NextVideo();
                }
                slider.setValue(newValue.toSeconds());
                actualDurationLabel.setText(String.valueOf((int)slider.getValue()));
            }));
            mediaPlayer.setOnReady(() -> {
                Duration totalDuration = media.getDuration();
                System.out.println(totalDuration);
                maxDurationLabel.setText(String.valueOf((int)totalDuration.toSeconds()));
                slider.setMax((int)totalDuration.toSeconds());
                System.out.println(slider.getValue());
            });
            String urlIcono = video.getIconoVideo();
            String urlIconoMedia = Directorio()+urlIcono;
            Image image = new Image(urlIconoMedia);
            iconoCancionImage.setImage(image);
            nombreArtistaLabel.setText(video.getArtistaVideo());
            nombreCancionLabel.setText(video.getNombreVideo());
            Thread.sleep(500);
            mediaPlayer.setAutoPlay(true);
        } catch (ExcepcionesVideo ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    public void siguienteTurno(){
        tt = new ThreadTurno(this, labelTurnoNuevo, turnoVBox, turnAlert, sigTurnoBtn);
        Thread newThread = new Thread(tt);
        newThread.start();
    }
    
    public void modificarVol(double volumeToDown){
        System.out.println("volumen: " + volumen);
        System.out.println("bajar: " + volumeToDown);
        volumen = volumen + volumeToDown;
        mediaPlayer.setVolume(volumen);
        System.out.println(volumen);
    }
    
    public double getVolumen(){
        return this.volumen;
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
    
    public String Directorio(){
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
