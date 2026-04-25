package bl.structures;

import bl.entities.Cliente;

public class ColaClientes {
    private NodoCliente frente;
    private NodoCliente fondo;

    public ColaClientes(){
    this.frente = null;
    this.fondo = null;
    }

    public boolean estaVacia(){
        return frente ==null;
    }

    public void insertar(Cliente cliente){
        NodoCliente nuevo = new NodoCliente(cliente);
        if(estaVacia()){
            frente = nuevo;
            fondo = nuevo;
            System.out.println("Cliente " + cliente.getNombre() + " agregado a la cola");
            return;
        }
        if(cliente.getPrioridad()>frente.getCliente().getPrioridad()){
            nuevo.setSiguiente(frente);
            frente = nuevo;
            System.out.println("Cliente " + cliente.getNombre() + " agregado al frente");
            return;
        }
        NodoCliente actual = frente;
        NodoCliente anterior = null;

        while(actual != null && actual.getCliente().getPrioridad() >= cliente.getPrioridad()){
            anterior = actual;
            actual = actual.getSiguiente();
        }
        anterior.setSiguiente(nuevo);
        nuevo.setSiguiente(actual);
        if(actual == null){
            fondo = nuevo;
        }
    }

    public Cliente atender(){
        if(estaVacia()){
            System.out.println("No hay clientes en cola");
            return null;
        }
        Cliente clienteAtendido = frente.getCliente();
        frente = frente.getSiguiente();
        if(frente == null){
            fondo = null;
        }
        System.out.println("Cliente atendido: " + clienteAtendido.getNombre());
        return clienteAtendido;
    }

    public Cliente verFrente(){
        if(estaVacia()){
            System.out.println("No hay clientes en la cola");
            return null;
        }
        return frente.getCliente();
    }

    public int getTamanio() {
        int contador = 0;
        NodoCliente actual = frente;
        while (actual != null) {
            contador++;
            actual = actual.getSiguiente();
        }
        return contador;
    }
}
