/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package User;

import java.util.LinkedList;

/**
 *
 * @author Carlos Auqui
 */
public class GestionPaciente {
    LinkedList<Paciente> listaPaciente = new LinkedList<>();
    
    public Paciente crearPaciente( String id, String nombre, Enfermedad enf){
        String IdPacienteF; // formato del paciente
        
        if(Integer.valueOf(id) <=0){
            System.out.println("id invalido");
            return null;
        }else{
            String formatId = "Pac0";
            String stID = formatId+id; // esto convierte el ide de 1 a pac01
            IdPacienteF = stID;
        }
        if(nombre == "" || nombre == " "){
            System.out.println("datos invalidos");
            return null;
        }
        if(enf == null){
            System.out.println("Enfermedad Nula");
            return null;
        }
        
        
        Paciente pacIng = new Paciente(IdPacienteF, nombre,enf);
        return pacIng;
    }

    public void addPaciente(Paciente paciente){
        listaPaciente.add(paciente);
    }
    
    public LinkedList<Paciente> returnListLink(){
        return listaPaciente;
    }
    
}
