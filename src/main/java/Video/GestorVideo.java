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
    public Video getNextVideo(Video bv) throws ExcepcionesVideo{
        if(!ListaVideo.contains(bv)){
            throw new ExcepcionesVideo("El video no se encuentra almacenado"); 
        }
        int IndexBase = ListaVideo.getIndex(bv);
        ListIterator<Video> VideoIterator = ListaVideo.listIterator(IndexBase);
        Video NextVideo = VideoIterator.next();
      return NextVideo;   
    }
    
    // metodo que retrocede al anterior video
    public Video getPrevVideo(Video bv) throws ExcepcionesVideo{
        if(!ListaVideo.contains(bv)){
            throw new ExcepcionesVideo("El video no se encuentra almacenado"); 
        }
        int IndexBase = ListaVideo.getIndex(bv);
        ListIterator<Video> VideoIterator = ListaVideo.listIterator(IndexBase);
        Video PrevVideo = VideoIterator.previous();
     return PrevVideo;
    }
    // metodo de loop constante de los elemetos del arreglo
    public Video loopVideo(Video bv){
        int indexVideo= ListaVideo.getIndex(bv);
        ListIterator<Video> VideoIterator = ListaVideo.listIterator(indexVideo);
        if(VideoIterator.hasNext()){
            Video videoActual = VideoIterator.next();           
        return videoActual;
        }
      return null;
    }
}
