public class NodoArbol {

    private Producto nodo;
    private final String llave;
    private NodoArbol izquierdo;
    private NodoArbol derecho;

    public NodoArbol(Producto nodo) {
        this.nodo = nodo;
        this.llave = nodo.getNombre();
    }

    public Producto getNodo() {
        return nodo;
    }

    public String getLlave() {
        return llave;
    }

    public NodoArbol getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoArbol izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoArbol getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoArbol derecho) {
        this.derecho = derecho;
    }

    @Override
    public String toString() {
        return "" + nodo;
    }
}
