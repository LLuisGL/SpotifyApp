/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Carlos Auqui
 */
public class gestionEnfermedad {
    Enfermedad enfermedad1 = new Enfermedad("Infarto agudo de miocardio",1);
    Enfermedad enfermedad2 = new Enfermedad("Accidente cerebrovascular",1);
    Enfermedad enfermedad3 = new Enfermedad("Neumonía grave",2);
    Enfermedad enfermedad4 = new Enfermedad("Meningitis bacteriana",2);
    Enfermedad enfermedad5 = new Enfermedad("Diabetes descontrolada",3);
    Enfermedad enfermedad6 = new Enfermedad("Insuficiencia renal crónica",3);
    Enfermedad enfermedad7 = new Enfermedad("Asma moderada",4);
    Enfermedad enfermedad8 = new Enfermedad("Hipertensión arterial controlada",4);
    Enfermedad enfermedad9 = new Enfermedad("Resfriado común",5);
    Enfermedad enfermedad10 = new Enfermedad("Gastritis",5);
    
    ArrayList<Enfermedad> listaEnfermedades = new ArrayList<Enfermedad>();
    
   /* public gestionEnfermedad(){
        listaEnfermedades.add(enfermedad1);
        listaEnfermedades.add(enfermedad2);
        listaEnfermedades.add(enfermedad3);
        listaEnfermedades.add(enfermedad4);
        listaEnfermedades.add(enfermedad5);
        listaEnfermedades.add(enfermedad6);
        listaEnfermedades.add(enfermedad7);
        listaEnfermedades.add(enfermedad8);
        listaEnfermedades.add(enfermedad9);
        listaEnfermedades.add(enfermedad10);
        
        guardarArchivoEnf(listaEnfermedades);    
    }
   
    
    public void guardarArchivoEnf(ArrayList<Enfermedad> listaEnfermedad){
        try{
            FileOutputStream objetoFile = new FileOutputStream(Directorio()+"/archivos/enfermedades.dat");
            ObjectOutputStream oos = new ObjectOutputStream(objetoFile);
            for(int i =0; i< listaEnfermedad.size(); i++){
                Enfermedad objetoEnf = listaEnfermedad.get(i);
                oos.writeObject(objetoEnf);
                System.out.println(" objeto escrito ");
            }
            oos.close();
        }catch(IOException  e){
            System.out.println("No se pudo realizar el guardado");
            e.printStackTrace();
        }
    }*/ // Usamos este metodo para agregar enfermedades a un archivo.
    
    public String Directorio(){
        String userDir = System.getProperty("user.dir");
        userDir = userDir.replace("\\", "/");
        
     return userDir;   
    }
    // este metodo lee el archivo 
    public ArrayList<Enfermedad> leerArchivoObjetos(){
       ArrayList<Enfermedad> listaEnfe = new ArrayList<>();
       try{
        FileInputStream objetoFile = new FileInputStream(Directorio()+"/archivos/enfermedades.dat");
        ObjectInputStream ois = new ObjectInputStream(objetoFile);
        do{ 
            Enfermedad enf = (Enfermedad) ois.readObject();
            listaEnfe.add(enf);
        }while(ois.readObject() != null);
        ois.close();
        System.out.println("Archivo leído correctamente. Total de enfermedades: " + listaEnfe.size());

       }catch(IOException e){
           e.printStackTrace();
       }catch(ClassNotFoundException e){
           e.printStackTrace();   
       }
       return listaEnfe;
    }
}
