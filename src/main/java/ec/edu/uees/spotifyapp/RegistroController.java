package ec.edu.uees.spotifyapp;

import ColaPrioridad.colaPacientePrio;
import User.Enfermedad;
import User.GestionPaciente;
import User.Paciente;
import User.gestionEnfermedad;
import java.net.URL;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Carlos Auqui
 */
public class RegistroController implements Initializable {
    gestionEnfermedad gestionEnf = new gestionEnfermedad();
    GestionPaciente gestionPac = new GestionPaciente();
    colaPacientePrio colaPrio = new colaPacientePrio();
    
    @FXML
    private ComboBox cmbEnfermedades;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtNombre;
    @FXML
    private Button regresar;
    
    /*MediaController controladorMedia = new MediaController();
    
    public RegistroController(){
        controladorMedia.rellerLabelsTurnos();
    }*/
    
    // crear al paciente
    public void crearPaciente(){
        String IdPaciente = txtId.getText();
        System.out.println(IdPaciente);
        String NombrePaciente = txtNombre.getText();
        System.out.println(NombrePaciente);
        System.out.println(cmbEnfermedades.getValue());
        Enfermedad enfermedadPaciente = gestionEnf.returnEnfermedad((String) cmbEnfermedades.getValue());
        System.out.println(enfermedadPaciente);
        
        Paciente pacC = gestionPac.crearPaciente(IdPaciente, NombrePaciente, enfermedadPaciente);
        gestionPac.addPaciente(pacC);
    }
    
    // metodo que llenara el comboBox con las enfermedades que estan en el archivo
    public void llenarComboBox(){
    ArrayList<Enfermedad> enfermedades = gestionEnf.leerArchivoObjetos();
    cmbEnfermedades.getItems().clear();
    
    for(int i = 0; i< enfermedades.size(); i++){
        Enfermedad enf = enfermedades.get(i);
        String nombreEnf = enf.getNombreEnfermedad();
        cmbEnfermedades.getItems().add(nombreEnf);
    }
    }
    public Label ActualizarLabel(){
        Label primerLabel = new Label();
        PriorityQueue<Paciente> ColaPrio =colaPrio.returnQueue();
        
        if(!ColaPrio.isEmpty()){
            Paciente primerElemento = ColaPrio.peek();
            primerLabel.setText(primerElemento.getId());
        }else{
            primerLabel.setText("Pac00");
        }    
        return primerLabel;
    }
    
    public void switchToMain(){
        Stage stage = (Stage) regresar.getScene().getWindow();
        stage.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarComboBox();
    }
    
    
}
