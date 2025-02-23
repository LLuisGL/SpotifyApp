/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ColaPrioridad;

import User.GestionPaciente;
import User.Paciente;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author Carlos Auqui
 */
public class colaPacientePrio {
    GestionPaciente gesPaciente = new GestionPaciente();
    LinkedList<Paciente> listaPaciente = gesPaciente.returnListLink();
    PriorityQueue<Paciente> pacientePrioritario = new PriorityQueue();
    
    public colaPacientePrio(){
       pacientePrioritario.addAll(listaPaciente);
    }
    
    public Paciente siguientePaciente(){
       Paciente sgtPaciente = pacientePrioritario.poll();
       if(sgtPaciente == null){
           System.out.println(" se llego al final");
           return null;
       }
       return sgtPaciente;
    }
    
    
}
