package Video;

/**
 *
 * @author Carlos Auqui
 */
public class Video {
    private String nombre_video;
    private String ruta;
    private double duracion;

    public Video(String nombre_video, String ruta) {
        this.nombre_video = nombre_video;
        this.ruta = ruta;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getNombre_video() {
        return nombre_video;
    }

    public void setNombre_video(String nombre_video) {
        this.nombre_video = nombre_video;
    }

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }
   
    
    
}
