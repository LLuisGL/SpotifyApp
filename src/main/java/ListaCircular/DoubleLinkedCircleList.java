/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ListaCircular;

import java.util.ListIterator;

/** 
 * @author Carlos Auqui
 */
public class DoubleLinkedCircleList<E> implements Lista<E>{
    private Node<E> first;
    private int current;

    
   
    class Node<E>{
        E data;
        Node<E> next;
        Node<E> prev;
        
        public Node(E e){
            this.data = e;
            this.next = this;
            this.prev = this;
        }
    }
    
    @Override
    public boolean addLast(E e) {
        Node<E> p = new Node<>(e);
        if(e == null){ 
            return false;
        }

        if(this.isEmpty()){
            first = p;
        }
        
        p.prev = first; // el previo del nuevo nodo apuntara a el ultimo nodo existente
        p.next= first.next; // el next del nuevo nodo apuntara al inicio
        first.next.prev = p; // el previo del primer nodo apunta al nuevo nodo 
        first.next = p; // el que era el ultimo no apuntara al nuevo nodo 
        first = p; // el nuevo nodo ahora es el ultimo
        current++;
        return true;       
    }

    @Override
    public boolean addFirst(E e) {
        Node<E> p = new Node<>(e);
        if(e == null){
            return false;
        }
        if(this.isEmpty()){
            first = p;
        }
        p.next = first.next; // esto hace que el nuevo nodo apunte al inicio
        p.prev = first.next.prev;// esto hace que el prev del nuevo nodo apunte al final 
        first.next.prev= p;
        first.prev = p;
        current++;
        return true;
    }

    @Override
    public E removeFirst() {
        if (this.isEmpty()){
            return null;
        }
        if (current == 1){
            first= null;
        }
        E tmp = first.next.data;
        first.next = first.next.next;
        Node<E> tmp1 = first.next.prev;
        first.next.prev = tmp1.prev;
        tmp1.next = null;
        tmp1.prev = null; 
        current--;
        return tmp; 
    }

    @Override
    public E removeLast() {
      if (this.isEmpty()){
            return null;
        }
        if (current == 1){
            first = null;
        }
        E tmp = first.data;
        first.next.prev = first.prev;
        first.prev.next = first.next;
        first.next = null;
        Node<E> tmp1 = first;
        first = first.prev;
        tmp1.prev = null;
        current--;
        return tmp;
    }

    @Override
    public E get(int index) {
        if(this.isEmpty()){
            return null;
        }
        if(index <0 || index > current){
            throw new IndexOutOfBoundsException("Indice no permitido");
        }
        Node<E> getter = SetUbicacion(index);
        E tmp = getter.data; //obtenemos el dato solicitado
        return tmp; 
    }

    @Override
    public boolean isEmpty() {
        Boolean validacion = (first == null);
        return validacion;  
    }

    @Override
    public int size() {
        return current; 
    }

    @Override
    public E remove(int index) {
        if(this.isEmpty()){ 
            return null;
        }
        if(index <0 || index > current){
            throw new IndexOutOfBoundsException("Indice no permitido");
        }
        if(index == 0){
            removeFirst();
        }
        if(index == current-1){
            removeLast();
        }
        Node<E> p = SetUbicacion(index); // ubicamos el nodo en el indice correspondiente
        Node<E> tmp1 = p; // ubicamos otro nodo temporal donde apunta p
        E tmp = p.data; // respaldamos la informacion de p
        p.next.prev = p.prev; // el nodo siguiente a p apunta al previo de p
        p.prev.next = p.next; // el nodo previo a p apunta al siguietne de p
        p.prev = null; // p ponde en nulo la referencia previa
        p = p.next; // ahora p se convierte en el nodo siguiente
        tmp1.next = null; // el nodo temporal sirve para poner en nulo la ultima referencia de p
        current--;
        return tmp;
    }

    @Override
    public boolean add(int index, E e) {
        if(this.isEmpty()){ 
            return false;
        }
        if(index <0 || index > current){
            throw new IndexOutOfBoundsException("Indice no permitido");
        }
        if(index == 0){
            addFirst(e);
        }
        if(index == current-1){
            addLast(e);
        }
        Node<E> p = SetUbicacion(index); // p se situa en el nodo que vamos a manipular para agg el nodeU
        Node<E> NodeU = new Node<>(e); // Nodo que vamos a agg en el indice indicado
        p.prev.next = NodeU; // hacemos que el nodo anterior a p apunte al nodeU que vamos a agg
        NodeU.next = p.next.prev; // hacemos que el nodeU apunte a p
        NodeU.prev = p.prev; // el prev de NodeU va a apuntar al prev de p
        p.prev = NodeU; // hacemos que el prev de p apunte al nuevo node agg        
        p = NodeU; // hacemos que NodeU se convierta en p, debido a los indices
        current++; 
        return true;    
    }

    @Override
    public boolean contains(E e) {
        if(this.isEmpty()){
            return false;
        } 
        Node<E> comp = first;
        for (int i = 0; i<current;i++){ //metodo que te va comparando cada data del nodo con equals
           if(comp.data.equals(e)){
               return true;
           }
           comp = comp.next;
        }
        return false;
    }
    
    // metodo creado que retornara el indice de un objeto pertenciente al arreglo
    @Override
    public int getIndex(E e) {
        if(this.isEmpty()){
            throw new IndexOutOfBoundsException("No existe el indice");
        }
        int indNode = 0;
        Node<E> comp = first.prev;
        for(int i= 0; i<current; i++){
            if(comp.data.equals(e)){
                return indNode;
            }
            comp = comp.next;
            indNode++;
        }
       throw new IndexOutOfBoundsException("No existe el indice"); 
    }
    
    private Node<E> SetUbicacion(int Index){
        Node<E> NodeUbi = first.next;
        for(int i= 0 ; i<Index;i++){
            NodeUbi = NodeUbi.next; // Este metodo nos permite situarnos un nodo antes del que nos pide el usuario, y el next no lleva al solicitado.
        }
        return NodeUbi;
    }
    
    public ListIterator<E> listIterator(){
        ListIterator<E> listI = new ListIterator<E>(){
            Node<E> traveler = first.next;
            int initial_index = 0;
            
            @Override
            public boolean hasNext() {
                return traveler != null;
            }

            @Override
            public E next() {
              traveler = traveler.next;
              E tmp = traveler.data;
              initial_index++;
              return tmp;
            }

            @Override
            public boolean hasPrevious() {
                return traveler != null;
            }

            @Override
            public E previous() {
                traveler = traveler.prev;
                E tmp = traveler.data;
                initial_index--;
                return tmp;
            }

            @Override
            public int nextIndex() {
               return (initial_index+1);
            }

            @Override
            public int previousIndex() {
                return (initial_index-1);
            }

            @Override
            public void remove() {
                if(initial_index == 0){
                    traveler = traveler.next;
                    removeFirst();
                  
                }
                if(initial_index == current-1){
                    traveler = traveler.prev;
                    removeLast();
                    initial_index--;
                    
                }
                traveler.next.prev = traveler.prev;
                traveler.prev.next = traveler.next;
                traveler.prev = null;
                Node<E> tmp = traveler;
                traveler = traveler.next;
                tmp.next= null;
                current--;
            }

            @Override
            public void set(E e) {
                traveler.data = e;
            }

            @Override
            public void add(E e) {
                if(initial_index == 0){
                    traveler = traveler.next;
                    addFirst(e);
                    initial_index++; 
                }
                if(initial_index == current-1){
                    addLast(e);
                    traveler = traveler.next;
                    initial_index++;
                }
                Node<E> NodeU = new Node<>(e);
                traveler.prev.next = NodeU;
                NodeU.next = traveler;
                NodeU.prev = traveler.prev;
                traveler.prev = NodeU;
                traveler = NodeU;
                current++;
            }            
        };
        return listI;
    }
    
}
