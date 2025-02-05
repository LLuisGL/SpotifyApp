/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ListaCircular;

/** 
 * @author Carlos Auqui
 */
public class DoubleLinkedCircleList<E> implements Lista<E>{
    private Node<E> last;
    private int current;
   
    class Node<E>{
        E data;
        Node<E> next;
        Node<E> prev;
        private int current;
        
        public Node(E e){
            this.data = e;
            this.next = this;
            this.prev = this;
        }
    }
    
    @Override
    public boolean addLast(E e) {
        if(e == null){ 
            return false;
        }
        Node<E> p = new Node<>(e);
        if(this.isEmpty()){
        last = p;
        last.prev = p;
        last.next = p;
        }
        
        p.next = last.next;
        last.next = p; 
        return true;
    }

    @Override
    public boolean addFirst(E e) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public E removeFirst() {
        throw new UnsupportedOperationException("Not supported yet.");   
    }

    @Override
    public E removeLast() {
        throw new UnsupportedOperationException("Not supported yet.");  
    }

    @Override
    public E get(int index) {
        throw new UnsupportedOperationException("Not supported yet.");  
    }

    @Override
    public boolean isEmpty() {
        Boolean validacion = (last == null);
        return validacion;  
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet.");  
    }

    @Override
    public E remove(int e) {
        throw new UnsupportedOperationException("Not supported yet.");  
    }

    @Override
    public boolean add(int index, E e) {
        throw new UnsupportedOperationException("Not supported yet.");  
    }

    @Override
    public boolean contains(E e) {
        throw new UnsupportedOperationException("Not supported yet.");  
    }
    
}
