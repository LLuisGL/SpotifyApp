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
    private Node<E> last;
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
            first = last = p;
        }
        
        p.next = last.next; // el nuevo nodo apunta al inicio 
        first.prev = p; // el previo del nodo inicial va a apuntar al nuevo nodo
        last.next = p; // el que en ese momento es el ultimo nodo va a apuntar ahora al nuevo nodo
        p.prev = last; // el previo del nuevo nodo apuntara al que es el ultimo en ese momento
        last = p; // ahora el ultimo sera el nodo ingresado
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
            first = last = p;
        }
        p.prev = first.prev; // el nuevo nodo apuntara a la misma direccion que el previo del primero
        first.prev = p; // ahora el previo apuntara a el nuevo nodo
        p.next = first;// el next del nuevo nodo apuntara a p
        last.next = p; // el next del ultimo nodo apuntara a p
        first = p; // ahora el primero nodo sera p,,
        current++;
        return true;
    }

    @Override
    public E removeFirst() {
        if (this.isEmpty()){
            return null;
        }
        if (current == 1){
            first= last = null;
        }
        E tmp = first.data; // aseguramos el dato del primer elemento para devolver
        last.next = first.next; // el next del ultimo nodo va a apuntar al next del primer nodo
        first.next.prev = first.prev; // el prev del next del primer nodo apuntara donde apunta el prev del primero nodo
        first.prev = null; //desconectamos el prev del  primer arreglo
        Node<E> tmp1 = first; // creamos un nodo que se situara en la ubicacion
        first = first.next; // ahora se ajusta el nodo siguiente como first
        tmp1.next = null; // desconectamos el nodo que era first del arreglo
        current--;
        return tmp; 
    }

    @Override
    public E removeLast() {
      if (this.isEmpty()){
            return null;
        }
        if (current == 1){
            first= last = null;
        }
        E tmp = last.data; // resguardamos los datos del ultimo nodo
        last.prev.next = last.next; // usamos el nodo previo para que apunte al nodo inicial
        last.next =null; // ahora el next del ultimo para desconectar
        first.prev = last.prev; // ahora el previo del primero apuntara al previo del ultimo
        Node<E> tmp1 = last; // se crea un nodo tmp que se situara en el ultimo nodo
        last = last.prev; // ahora el nodo previo sera el ultimo
        tmp1.prev =null; // desde el nodo tmp se desconectara la ultima referencia 
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
        Boolean validacion = (first == null && last == null);
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
           if(comp.equals(e)){
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
        Node<E> comp = first;
        for(int i= 0; i<current; i++){
            if(comp.equals(e)){
                return indNode;
            }
            comp = comp.next;
            indNode++;
        }
       throw new IndexOutOfBoundsException("No existe el indice"); 
    }
    
    private Node<E> SetUbicacion(int Index){
        Node<E> NodeUbi = first;
        for(int i= 0 ; i<Index;i++){
            NodeUbi = NodeUbi.next; // Este metodo nos permite situarnos un nodo antes del que nos pide el usuario, y el next no lleva al solicitado.
        }
        return NodeUbi;
    }
    
    public ListIterator<E> listIterator(){
        ListIterator<E> listI = new ListIterator<E>(){
            Node<E> traveler = first;
            int initial_index = 0;
            @Override
            public boolean hasNext() {
                return traveler != null;
            }

            @Override
            public E next() {
              E tmp = traveler.data;
              traveler = traveler.next;
              initial_index++;
              return tmp;
            }

            @Override
            public boolean hasPrevious() {
                return traveler != null;
            }

            @Override
            public E previous() {
                E tmp = traveler.data;
                traveler = traveler.prev;
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
