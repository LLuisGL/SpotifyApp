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
    
    public Paciente crearPaciente( int id, String nombre, Enfermedad enf){
        if(id <=0){
            System.out.println("id invalido");
            return null;
        }
        if(nombre == "" || nombre == " "){
            System.out.println("datos invalidos");
            return null;
        }
        if(enf == null){
            System.out.println("Enfermedad Nula");
            return null;
        }
        Paciente pacIng = new Paciente(id, nombre,enf);
        return pacIng;
    }

    public void addPaciente(Paciente paciente){
        listaPaciente.add(paciente);
    }
    
    public LinkedList<Paciente> returnListLink(){
        return listaPaciente;
    }
    
}
