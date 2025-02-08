/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package User;

import ListaCircular.DoubleLinkedCircleList;
import java.util.ListIterator;


/**
 *
 * @author Carlos Auqui
 */
public class GestorUsuario {
    DoubleLinkedCircleList<Usuario> ListaUser = new DoubleLinkedCircleList<>();
    
    public void addLast(Usuario u){
        ListaUser.addLast(u);
    }
    public Boolean verificarUsuario(String usecomp, String contra){
        Usuario useComp = new Usuario(usecomp,contra);
     return(ListaUser.contains(useComp));
    }    
}
