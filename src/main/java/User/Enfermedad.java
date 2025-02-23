package User;

import java.io.Serializable;

/**
 *
 * @author Carlos Auqui
 */
public class Enfermedad implements Serializable{
    String nombreEnfermedad;
    int prioridad;
    
    public Enfermedad(String nombreEnf, int prio){
        this.nombreEnfermedad = nombreEnf;
        this.prioridad = prio;
    }

    public String getNombreEnfermedad() {
        return nombreEnfermedad;
    }

    public void setNombreEnfermedad(String nombreEnfermedad) {
        this.nombreEnfermedad = nombreEnfermedad;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }
    
}
