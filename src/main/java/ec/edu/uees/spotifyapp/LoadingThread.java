package ec.edu.uees.spotifyapp;

import java.io.IOException;

public class LoadingThread extends Thread  {
    public void run(){
        try {
            Thread.sleep(1700);
            App.setRoot("login");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
