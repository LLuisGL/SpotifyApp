package Video;

import java.time.Duration;

/**
 *
 * @author Carlos Auqui
 */
public class Video {
    private String nombreVideo;
    private String artistaVideo;
    private String colorVideo;
    
    private String ruta;
    private Duration duracion;

    public Video(String nombreVideo, String artistaVideo, String colorVideo, String ruta, Duration duracion) {
        this.nombreVideo = nombreVideo;
        this.artistaVideo = artistaVideo;
        this.ruta = ruta;
        this.duracion = duracion;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getNombre_video() {
        return nombreVideo;
    }

    public void setNombre_video(String nombre_video) {
        this.nombreVideo = nombreVideo.toUpperCase();
    }

    public Duration getDuracion() {
        return duracion;
    }

    public void setDuracion(Duration duracion) {
        this.duracion = duracion;
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
