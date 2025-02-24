/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package User;

/**
 *
 * @author Carlos Auqui
 */
public class Paciente implements Comparable<Paciente> {
    String id;
    String nombrePacie;
    Enfermedad enfermedad;

    public Paciente(String id, String nombrePacie, Enfermedad enfermedad) {
        this.id = id;
        this.nombrePacie = nombrePacie;
        this.enfermedad = enfermedad;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    
    public String getNombrePacie() {
        return nombrePacie;
    }

    public void setNombrePacie(String nombrePacie) {
        this.nombrePacie = nombrePacie;
    }

    public Enfermedad getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(Enfermedad enfermedad) {
        this.enfermedad = enfermedad;
    }
    // metodo que compara los pacientes
    @Override
    public int compareTo(Paciente p) {
        return Integer.compare(this.enfermedad.getPrioridad(), p.enfermedad.getPrioridad());
    }
    
    
}
