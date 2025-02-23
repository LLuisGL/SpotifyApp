package ec.edu.uees.spotifyapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class ThreadTurno implements Runnable {
    
    
    //IM COOKING 
    
    
    private MediaController mc;
    private Label labelTurnoNuevo;
    private VBox turnoVBox;
    private MediaView turnAlert;
    private String turnDirectory;
    private Button sigTurnBtn;
    
    private boolean flag = true;
    
    public ThreadTurno(MediaController mc, Label labelTurnoNuevo, VBox turnoVBox, MediaView turnAlert, Button sigTurnBtn){
        this.mc = mc;
        this.labelTurnoNuevo = labelTurnoNuevo;
        this.turnoVBox = turnoVBox;
        this.turnAlert = turnAlert;
        this.turnDirectory = mc.Directorio()+"/media/turnAlert.mp3";
        this.sigTurnBtn = sigTurnBtn;
    }
    
    
    @Override
    public void run() {
        if(flag){
            sigTurnBtn.setDisable(true);
            Media media = new Media(turnDirectory);
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            turnAlert.setMediaPlayer(mediaPlayer);
            mediaPlayer.play();
            System.out.println("hola, soy el thread4");
            Thread threadVolume = new Thread(new Runnable(){
                public void run(){
                    for(int i=0;i<100;i++){
                        if(mc.getVolumen()<=0.31){
                            break;
                        }
                        try {
                            Thread.sleep(250);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        mc.modificarVol(-0.1);
                    }
                }
            });
            threadVolume.start();
            for(double i=0; i<1;i=i+0.1){
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                turnoVBox.setOpacity(i);
            }
            try {
                Thread.sleep(2500);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            Thread threadVolume2 = new Thread(new Runnable(){
                public void run(){
                    for(int i=0;i<100;i++){
                        if(mc.getVolumen()>=1){
                            break;
                        }
                        try {
                            Thread.sleep(250);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        mc.modificarVol(+0.1);
                    }
                }
            });
            threadVolume2.start();
            for(double i=1; i>0;i=i-0.1){
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                turnoVBox.setOpacity(i);
            }
            flag = false;
            sigTurnBtn.setDisable(false);
        }
    }
    
}
