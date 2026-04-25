package bl.structures;

import bl.entities.Producto;

public class NodoLista {

    private Producto nodo;
    private NodoLista siguiente;

    public NodoLista(Producto nodo) {
        this.nodo = nodo;
    }

    public Producto getNodo() {
        return nodo;
    }

    public void setNodo(Producto nodo) {
        this.nodo = nodo;
    }

    public NodoLista getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoLista siguiente) {
        this.siguiente = siguiente;
    }

    @Override
    public String toString() {
        return nodo.toString();
    }
}
