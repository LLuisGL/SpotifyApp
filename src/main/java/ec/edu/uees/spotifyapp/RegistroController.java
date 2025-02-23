package ec.edu.uees.spotifyapp;

import User.Enfermedad;
import User.gestionEnfermedad;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

/**
 *
 * @author Carlos Auqui
 */
public class RegistroController implements Initializable {
    gestionEnfermedad gestionEnf = new gestionEnfermedad();
    @FXML
    private ComboBox cmbEnfermedades;
    
    private void llenarComboBox(){
    ArrayList<Enfermedad> enfermedades = gestionEnf.leerArchivoObjetos();
    cmbEnfermedades.getItems().clear();
    for(int i = 0; i < enfermedades.size(); i++){
        Enfermedad enf = enfermedades.get(i);
        cmbEnfermedades.getItems().add(enf.getNombreEnfermedad());
      }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarComboBox();
    }
    
    
}
