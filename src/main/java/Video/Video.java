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
    private String URlVideo;

    public Video(String nombreVideo, String artistaVideo, String colorVideo, String URlVideo) {
        this.nombreVideo = nombreVideo;
        this.artistaVideo = artistaVideo;
        this.colorVideo = colorVideo;
        this.URlVideo = URlVideo;
    }

    public String getURlVideo() {
        return URlVideo;
    }

    public void setURlVideo(String URLVideo) {
        this.URlVideo = URlVideo;
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
