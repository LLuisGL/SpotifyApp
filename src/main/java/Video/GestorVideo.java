package Video;

import ListaCircular.DoubleLinkedCircleList;
import java.util.ListIterator;
import javafx.scene.media.Media;

/**
 *
 * @author Carlos Auqui
 */
public class GestorVideo {
    DoubleLinkedCircleList<Video> ListaVideo = new DoubleLinkedCircleList<>();
    ListIterator<Video> VideoIterator = ListaVideo.listIterator();
    
    // metodo que permitira agregar un nuevo video al arreglo
    public void addVideo(String nombre, String artistaVideo, String colorVideo, Media archivoVideo){
        Video v = new Video(nombre, artistaVideo, colorVideo, archivoVideo);
        ListaVideo.addLast(v);
    }
    // metodo que permite eliminar un video del arreglo
    public void removeVideo(Video vR) throws ExcepcionesVideo{
        Boolean verificacion = ListaVideo.contains(vR);
        if(verificacion){
            int indexRemove = ListaVideo.getIndex(vR);
            ListaVideo.remove(indexRemove);
        }
        else{
           throw new ExcepcionesVideo("Video No econtrado");
        }
    }
    // metodo que avanzara al siguiente video
    public Video getNextVideo() throws ExcepcionesVideo{
        if (VideoIterator.hasNext()){
            Video NextVideo = VideoIterator.next();
            return NextVideo;
        }
        else{
            throw new ExcepcionesVideo("No hay un video siguiente");
        }
    }
    
    // metodo que retrocede al anterior video
    public Video getPrevVideo() throws ExcepcionesVideo{
        if(VideoIterator.hasNext()){
            Video PrevVideo = VideoIterator.previous();
            return PrevVideo;
        }
        else{
           throw new ExcepcionesVideo("No hay un video siguiente");  
        }
    }

}
