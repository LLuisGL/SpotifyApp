package Video;

import ListaCircular.DoubleLinkedCircleList;
import java.util.ListIterator;
import javafx.scene.media.Media;

/**
 *
 * @author Carlos Auqui
 */
public class GestorVideo {
    
    Video v1 = new Video("Bring me to life","Evanenscence","verde","/media/song1.mp4", "/media/song1.jpg");
    Video v2 = new Video("Dragon Ball Rap","Porta", "Rojo","/media/song2.mp4", "/media/song2.jpeg");
    Video v3 = new Video("Rebota","Guayana", "Azul", "media/song3.mp4", "/media/song3.jpg");
    Video v4 = new Video("In the End","Linking par", "Azul", "media/song4.mp4", "/media/song4.jpeg");
    Video v5 = new Video("Titi me pregunto ","Bad bo", "Azul", "media/song5.mp4", "/media/song5.jpeg");
    
    DoubleLinkedCircleList<Video> ListaVideo = new DoubleLinkedCircleList<>();
    ListIterator<Video> VideoIterator;
    
    public GestorVideo(){
        ListaVideo.addLast(v1);
        ListaVideo.addLast(v2);
        ListaVideo.addLast(v3);
        ListaVideo.addLast(v4);
        ListaVideo.addLast(v5);
        
        VideoIterator = ListaVideo.listIterator();
    }
    
// metodo que permitira agregar un nuevo video al arreglo
    public void addVideo(Video VI){
        ListaVideo.addLast(VI);
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
