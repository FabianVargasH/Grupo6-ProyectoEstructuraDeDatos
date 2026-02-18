import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ListaProductos {
    private Producto primero;

    public ListaProductos(){
        primero = null;
    }
    public Producto getPrimero(){
        return primero;
    }
    //Validacion para saber si la lista esta vacia
    private boolean estaVacia(){
        return primero == null;
    }
    //Insertar producto al inicicio
    public void insertarInicio(Producto producto){
        producto.setSiguiente(primero);
        primero = producto;
    }
    //Insertar producto al final
    public void insertarFinal(Producto producto){
        if(estaVacia()){
            primero = producto;
            return;
        }
        Producto temp = primero;
        while(temp.getSiguiente() != null){
            temp = temp.getSiguiente();
        }
        temp.setSiguiente(producto);
    }
    //Mostrar lista de productos
    public void mostrarLista(){
        if(estaVacia()){
            System.out.println("La lista esta vacia");
            return;
        }
        Producto temp = primero;
        while(temp != null){
            System.out.println(temp);
            System.out.println("- - - - - - - - - - - - - - - -");
            temp = temp.getSiguiente();
        }
    }

    public Producto buscar(String nombre){
        Producto temp = primero;

        while(temp != null && !temp.getNombre().equalsIgnoreCase(nombre)){
            temp = temp.getSiguiente();
        }
        return temp;
    }
    //Modificar Productos
    public void modificar() throws IOException {

    if (estaVacia()) {
        System.out.println("La lista esta vacia");
        return;
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    System.out.println("- - Modificar Producto - -");
    mostrarLista();

    System.out.print("\nIngrese el nombre del producto que quiere modificar: ");
    String nombreBuscar = reader.readLine();

    Producto productoModificar = buscar(nombreBuscar);

    if (productoModificar != null) {

        System.out.println("\nDatos actuales del producto:");
        System.out.println("Nombre: " + productoModificar.getNombre());
        System.out.println("Categoria: " + productoModificar.getCategoria());
        System.out.println("Fecha Vencimiento: " + productoModificar.getFechaVencimiento());
        System.out.println("Precio: " + productoModificar.getPrecio());
        System.out.println("Cantidad: " + productoModificar.getCantidad());

        System.out.println("\nIngrese los nuevos datos:");

        System.out.print("Nueva categoria: ");
        String nuevaCategoria = reader.readLine();
        productoModificar.setCategoria(nuevaCategoria);

        System.out.print("Nueva fecha de vencimiento: ");
        String nuevaFecha = reader.readLine();
        productoModificar.setFechaVencimiento(nuevaFecha);

        System.out.print("Nuevo precio: ");
        double nuevoPrecio = Double.parseDouble(reader.readLine());
        if (nuevoPrecio > 0) {
            productoModificar.setPrecio(nuevoPrecio);
        } else {
            System.out.println("El precio debe ser mayor a 0. Se mantiene el anterior.");
        }

        System.out.print("Nueva cantidad: ");
        int nuevaCantidad = Integer.parseInt(reader.readLine());
        if (nuevaCantidad >= 0) {
            productoModificar.setCantidad(nuevaCantidad);
        } else {
            System.out.println("La cantidad no puede ser negativa. Se mantiene la anterior.");
        }

        System.out.print("Desea agregar una nueva imagen? (s/n): ");
        String opcion = reader.readLine();

        if (opcion.equalsIgnoreCase("s")) {
            System.out.print("Ingrese la ruta de la imagen: ");
            String nuevaImagen = reader.readLine();
            productoModificar.agregarImagenes(nuevaImagen);
            System.out.println("Imagen agregada correctamente.");
        }

        System.out.println("\n- - Producto modificado exitosamente - -");
    }
}
    //Eliminacion de productos
    public void eliminar(String nombre){
        if(estaVacia()){
            System.out.println("Lista de productos vacía");
            return;
        }
        Producto temp = primero;
        Producto anterior = null;

        while(temp != null && !temp.getNombre().equalsIgnoreCase(nombre)){
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
        Producto temp = primero;
        double costoTotalLista = 0;

        while(temp != null){
            double costoTotalProducto = temp.calcularCostoTotal();

            System.out.println("Producto: " + temp.getNombre());
            System.out.println("Precio unitario: " + temp.getPrecio());
            System.out.println("Cantidad: " + temp.getCantidad());
            System.out.println("Costo total del producto: " + costoTotalProducto);
            System.out.println("- - - - - - - - - - - - - - - - - - -");

            costoTotalLista += costoTotalProducto;
            temp = temp.getSiguiente();
        }
        System.out.println("- - - Costo Total General - - -");
        System.out.println(costoTotalLista);
    }
}
