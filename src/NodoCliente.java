public class NodoCliente {
    private Cliente cliente;
    private NodoCliente siguiente;

    public NodoCliente(Cliente cliente){
        this.cliente = cliente;
        this.siguiente = null;
    }

    //Getters
    public Cliente getCliente(){
        return cliente;
    }
    public NodoCliente getSiguiente(){
        return siguiente;
    }
    //Setters
    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }
    public void setSiguiente(NodoCliente siguiente){
        this.siguiente = siguiente;
    }

    @Override
    public String toString(){
        return cliente.toString();
    }
}
