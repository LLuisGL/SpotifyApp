package Video;

import java.time.Duration;
import javafx.scene.media.Media;

/**
 *
 * @author Carlos Auqui
 */
public class Video {
    private String nombreVideo;
    private String artistaVideo;
    private String colorVideo;   
    private Media archivoVideo;

    public Video(String nombreVideo, String artistaVideo, String colorVideo, Media archivoVideo) {
        this.nombreVideo = nombreVideo;
        this.artistaVideo = artistaVideo;
        this.colorVideo = colorVideo;
        this.archivoVideo = archivoVideo;
    }

    public Media getMedia() {
        return archivoVideo;
    }

    public void setMedia(Media archivoVideo) {
        this.archivoVideo = archivoVideo;
    }

    public String getNombre_video() {
        return nombreVideo;
    }

    public void setNombre_video(String nombre_video) {
        this.nombreVideo = nombreVideo.toUpperCase();
    }

    public String getNombreVideo() {
        return nombreVideo;
    }

    public void setNombreVideo(String nombreVideo) {
        this.nombreVideo = nombreVideo;
    }

    public String getArtistaVideo() {
        return artistaVideo;
    }

    public void setArtistaVideo(String artistaVideo) {
        this.artistaVideo = artistaVideo;
    }

    public String getColorVideo() {
        return colorVideo;
    }

    public void setColorVideo(String colorVideo) {
        this.colorVideo = colorVideo;
    }
   
    
    
}
