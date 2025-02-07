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
    private String iconoVideo;

    public Video(String nombreVideo, String artistaVideo, String colorVideo, String URlVideo, String iconoVideo) {
        this.nombreVideo = nombreVideo;
        this.artistaVideo = artistaVideo;
        this.colorVideo = colorVideo;
        this.URlVideo = URlVideo;
        this.iconoVideo = iconoVideo;
    }

    public String getURlVideo() {
        return URlVideo;
    }

    public String getIconoVideo() {
        return iconoVideo;
    }

    public void setIconoVideo(String iconoVideo) {
        this.iconoVideo = iconoVideo;
    }
    
    public void setURlVideo(String URLVideo) {
        this.URlVideo = URlVideo;
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
