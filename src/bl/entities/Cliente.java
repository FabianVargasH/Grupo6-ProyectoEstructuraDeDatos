package bl.entities;

import bl.structures.ListaProductos;
import bl.structures.NodoLista;

public class Cliente {
    private String nombre;
    private int prioridad;
    private ListaProductos carrito;
    private String ubicacion; // Nueva: ubicación del cliente (vértice en el Grafo)

    public Cliente(){}

    public Cliente(String nombre, int prioridad, String ubicacion){
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.ubicacion = ubicacion;
        this.carrito = new ListaProductos();
    }

    // Getters
    public String getNombre(){
        return nombre;
    }
    public int getPrioridad(){
        return prioridad;
    }
    public ListaProductos getCarrito(){
        return carrito;
    }
    public String getUbicacion(){
        return ubicacion;
    }

    // Setters
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setPrioridad(int prioridad){
        if(prioridad >= 1 && prioridad <= 3){
            this.prioridad = prioridad;
        }
    }
    public void setUbicacion(String ubicacion){
        this.ubicacion = ubicacion;
    }

    public void agregarAlCarrito(Producto producto, int cantidad){
        Producto productoCarrito = new Producto(
                producto.getNombre(),
                producto.getPrecio(),
                producto.getCategoria(),
                producto.getFechaVencimiento(),
                cantidad
        );
        carrito.insertarFinal(productoCarrito);
    }

    public double calcularTotalCarrito(){
        double total = 0;
        NodoLista temp = carrito.getPrimero();
        while(temp != null){
            total += temp.getNodo().calcularCostoTotal();
            temp = temp.getSiguiente();
        }
        return total;
    }

    public void mostrarCarrito(){
        System.out.println("\n--- Carrito de " + nombre + " ---");
        carrito.mostrarLista();
    }

    public void vaciarCarrito(){
        carrito.vaciarLista();
        System.out.println("Carrito vaciado correctamente");
    }

    public int getCantidadProductos(){
        int contador = 0;
        NodoLista temp = carrito.getPrimero();
        while(temp != null){
            contador++;
            temp = temp.getSiguiente();
        }
        return contador;
    }

    public String toString(){
        String tipoPrioridad = "";
        switch (prioridad){
            case 1: tipoPrioridad = "Basico";    break;
            case 2: tipoPrioridad = "Afiliado";  break;
            case 3: tipoPrioridad = "Premium";   break;
        }
        return "\nbl.entities.Cliente: " + nombre +
                "\nPrioridad: " + tipoPrioridad +
                "\nUbicación: " + ubicacion +
                "\nProductos: " + getCantidadProductos();
    }
}