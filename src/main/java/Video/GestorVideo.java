package Video;

import ListaCircular.DoubleLinkedCircleList;
import java.util.ListIterator;
import javafx.scene.media.Media;

/**
 *
 * @author Carlos Auqui
 */
public class GestorVideo {
    
    Video v1 = new Video("Bring Me to Life","Evanenscence","007eb0","/media/song1.mp4", "/media/song1.jpg");
    Video v2 = new Video("Dragon Ball Rap","Porta", "e38509","/media/song2.mp4", "/media/song2.jpeg");
    Video v3 = new Video("BAILE INoLVIDABLE","Bad Bunny", "b00000", "media/song3.mp4", "/media/song3.png");
    Video v4 = new Video("In The End","Linking Park", "1e1e1e", "media/song4.mp4", "/media/song4.jpeg");
    Video v5 = new Video("Nunca Muda ","NXGHT", "8e26e9", "media/song5.mp4", "/media/song5.jpeg");
    
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
    
    public Video getFirstVideo(){
        Video firstVideo = ListaVideo.get(0);
        return firstVideo; 
    }

}
