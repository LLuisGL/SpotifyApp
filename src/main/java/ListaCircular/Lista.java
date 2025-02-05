package ListaCircular;

/*
 * @author Carlos Auqui
 */
public interface Lista<E>  {
    boolean addLast(E e);
    boolean addFirst(E e);
    E removeFirst();
    E removeLast();
    E get(int index);
    boolean isEmpty();
    int size();
    E remove(int e);
    boolean add(int index, E e);
    boolean contains(E e);
}
