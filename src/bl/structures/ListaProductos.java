package bl.structures;

import bl.entities.Producto;

public class ListaProductos {
    private NodoLista primero;

    public ListaProductos(){
        primero = null;
    }
    public NodoLista getPrimero(){
        return primero;
    }

    //Validacion para saber si la lista esta vacia
    private boolean estaVacia(){
        return primero == null;
    }
    //Insertar producto al inicicio
    public void insertarInicio(Producto producto){

        NodoLista nodo = new NodoLista(producto);
        nodo.setSiguiente(primero);
        primero = nodo;
    }
    //Insertar producto al final
    public void insertarFinal(Producto producto){

        NodoLista nodo = new NodoLista(producto);

        if(estaVacia()){
            primero = nodo;
            return;
        }
        NodoLista temp = primero;
        while(temp.getSiguiente() != null){
            temp = temp.getSiguiente();
        }
        temp.setSiguiente(nodo);
    }
    //Mostrar lista de productos
    public void mostrarLista(){
        if(estaVacia()){
            System.out.println("La lista esta vacia");
            return;
        }
        NodoLista temp = primero;
        while(temp != null){
            System.out.println(temp.getNodo());
            System.out.println("- - - - - - - - - - - - - - - -");
            temp = temp.getSiguiente();
        }
    }

    public NodoLista buscar(String nombre){
        NodoLista temp = primero;

        while(temp != null && !temp.getNodo().getNombre().equalsIgnoreCase(nombre)){
            temp = temp.getSiguiente();
        }

        return temp;
    }
    //Eliminacion de productos
    public void eliminar(String nombre){
        if(estaVacia()){
            System.out.println("Lista de productos vacía");
            return;
        }
        NodoLista temp = primero;
        NodoLista anterior = null;

        while(temp != null && !temp.getNodo().getNombre().equalsIgnoreCase(nombre)){
            anterior = temp;
            temp = temp.getSiguiente();
        }
        if(temp == null){
            System.out.println("bl.entities.Producto no encontrado");
            return;
        }
        if(anterior == null){
            primero = temp.getSiguiente();
        }else{
            anterior.setSiguiente(temp.getSiguiente());
        }
        System.out.println("bl.entities.Producto eliminado correctamente");
    }
    //Metodo para vaciar el carrito sin niguna impresion
    public void vaciarLista(){
        primero = null;
    }

    //Reporte de los costos
    public void reporteCostos(){
        if(estaVacia()){
            System.out.println("Lista de productos esta vacia");
            return;
        }
        NodoLista temp = primero;
        double costoTotalLista = 0;

        while(temp != null){
            double costoTotalProducto = temp.getNodo().calcularCostoTotal();

            System.out.println("bl.entities.Producto: " + temp.getNodo().getNombre());
            System.out.println("Precio unitario: " + temp.getNodo().getPrecio());
            System.out.println("Cantidad: " + temp.getNodo().getCantidad());
            System.out.println("Costo total del producto: " + costoTotalProducto);
            System.out.println("- - - - - - - - - - - - - - - - - - -");

            costoTotalLista += costoTotalProducto;
            temp = temp.getSiguiente();
        }
        System.out.println("- - - Costo Total General - - -");
        System.out.println(costoTotalLista);
    }
}
