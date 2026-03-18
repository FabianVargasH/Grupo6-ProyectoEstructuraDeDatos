import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
            System.out.println(temp);
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
//    //Modificar Productos
//    public void modificar() throws IOException {
//
//    if (estaVacia()) {
//        System.out.println("La lista esta vacia");
//        return;
//    }
//
//    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//
//    System.out.println("- - Modificar Producto - -");
//    mostrarLista();
//
//    System.out.print("\nIngrese el nombre del producto que quiere modificar: ");
//    String nombreBuscar = reader.readLine();
//
//    NodoLista productoModificar = buscar(nombreBuscar);
//
//    if (productoModificar != null) {
//
//        System.out.println("\nDatos actuales del producto:");
//        System.out.println("Nombre: " + productoModificar.getNodo().getNombre());
//        System.out.println("Categoria: " + productoModificar.getNodo().getCategoria());
//        System.out.println("Fecha Vencimiento: " + productoModificar.getNodo().getFechaVencimiento());
//        System.out.println("Precio: " + productoModificar.getNodo().getPrecio());
//        System.out.println("Cantidad: " + productoModificar.getNodo().getCantidad());
//
//        System.out.println("\nIngrese los nuevos datos:");
//
//        System.out.print("Nueva categoria: ");
//        String nuevaCategoria = reader.readLine();
//        productoModificar.getNodo().setCategoria(nuevaCategoria);
//
//        System.out.print("Nueva fecha de vencimiento: ");
//        String nuevaFecha = reader.readLine();
//        productoModificar.getNodo().setFechaVencimiento(nuevaFecha);
//
//        System.out.print("Nuevo precio: ");
//        double nuevoPrecio = Double.parseDouble(reader.readLine());
//        if (nuevoPrecio > 0) {
//            productoModificar.getNodo().setPrecio(nuevoPrecio);
//        } else {
//            System.out.println("El precio debe ser mayor a 0. Se mantiene el anterior.");
//        }
//
//        System.out.print("Nueva cantidad: ");
//        int nuevaCantidad = Integer.parseInt(reader.readLine());
//        if (nuevaCantidad >= 0) {
//            productoModificar.getNodo().setCantidad(nuevaCantidad);
//        } else {
//            System.out.println("La cantidad no puede ser negativa. Se mantiene la anterior.");
//        }
//
//        System.out.print("Desea agregar una nueva imagen? (s/n): ");
//        String opcion = reader.readLine();
//
//        if (opcion.equalsIgnoreCase("s")) {
//            System.out.print("Ingrese la ruta de la imagen: ");
//            String nuevaImagen = reader.readLine();
//            productoModificar.getNodo().agregarImagenes(nuevaImagen);
//            System.out.println("Imagen agregada correctamente.");
//        }
//
//        System.out.println("\n- - Producto modificado exitosamente - -");
//    }
//}
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
            System.out.println("Producto no encontrado");
            return;
        }
        if(anterior == null){
            primero = temp.getSiguiente();
        }else{
            anterior.setSiguiente(temp.getSiguiente());
        }
        System.out.println("Producto eliminado correctamente");
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

            System.out.println("Producto: " + temp.getNodo().getNombre());
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
