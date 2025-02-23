package ec.edu.uees.spotifyapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ThreadTurno implements Runnable {
    
    
    //IM COOKING 
    
    
    private MediaController mc;
    private Label labelTurnoNuevo;
    private VBox turnoVBox;
    
    private boolean flag = true;
    
    public ThreadTurno(MediaController mc, Label labelTurnoNuevo, VBox turnoVBox){
        this.mc = mc;
        this.labelTurnoNuevo = labelTurnoNuevo;
        this.turnoVBox = turnoVBox;
    }
    
    
    @Override
    public void run() {
        if(flag){
            System.out.println("hola, soy el thread4");
            mc.modificarVol(-0.5);
            for(double i=0; i<1;i=i+0.1){
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                turnoVBox.setOpacity(i);
                System.out.println(i);
            }
            try {
                Thread.sleep(2500);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            mc.modificarVol(+0.5);
            for(double i=1; i>40;i=i-0.1){
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                turnoVBox.setOpacity(i);
            }
            flag = false;
        }
    }
    
}
