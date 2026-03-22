import java.util.ArrayList;

public class Tienda {

    private ArbolProductos inventario;
    private ArrayList<Producto> productosAnnadidos = new ArrayList<>();
    private ColaClientes colaClientes;

    public Tienda(ArbolProductos inventario) {
        this.inventario = inventario;
        this.productosAnnadidos = new ArrayList<>();
        this.colaClientes = new ColaClientes();
    }

    public ColaClientes getColaClientes(){
        return colaClientes;
    }

    public ArrayList<Producto> getProductosAnnadidos() {
        return productosAnnadidos;
    }

    public void agregarProducto(Producto producto){

        Producto existente = buscarProducto(producto.getNombre());

        if (existente != null) {
            System.out.println("\nError: El producto ya existe en el inventario.");
        } else {
            inventario.insertar(producto);
            productosAnnadidos.add(producto);
            System.out.println("El producto fue agregado al inventario.");
        }
    }

    public Producto buscarProducto(String nombre) {
        NodoArbol producto = inventario.buscar(nombre);

        if (producto == null) return null;
        return producto.getNodo();
    }

    public Producto eliminarProducto(String nombre){
        NodoArbol producto = inventario.eliminar(nombre);

        if (producto == null) return null;
        productosAnnadidos.remove(producto.getNodo());
        return producto.getNodo();
    }

    public void mostrarInventario() {
        if (inventario.estaVacia()) {
            System.out.println("=== INVENTARIO ====");
            System.out.println("\nEl inventario está vacio.");
        } else {
            System.out.println("=== INVENTARIO ====");
            inventario.mostrarInventario(inventario.getRaiz());
        }
    }

    public void reporteCostos(){

        if (inventario.estaVacia()) {
            System.out.println("\n=== REPORTE DE COSTOS ===");
            System.out.println("\nEl inventario está vacío.");
        } else {
            System.out.println("\n=== REPORTE DE COSTOS ===");
            double costoTotalLista = 0;

            for (int i = 0; i < productosAnnadidos.size(); i++) {
                Producto temp = productosAnnadidos.get(i);

                System.out.println("Producto: " + temp.getNombre());
                System.out.println("Precio unitario: " + temp.getPrecio());
                System.out.println("Cantidad: " + temp.getCantidad());
                System.out.println("Costo total del producto: " + temp.calcularCostoTotal());
                System.out.println("- - - - - - - - - - - - - - - - - - -");

                costoTotalLista += temp.calcularCostoTotal();
            }

            System.out.println("- - - Costo Total General - - -");
            System.out.println(costoTotalLista);

        }
    }

    public void agregarClienteCola(Cliente cliente){
        colaClientes.insertar(cliente);
    }

    public Cliente atenderSiguienteCliente(){
        return colaClientes.atender();
    }

    public Cliente verProximoCliente(){
        return colaClientes.verFrente();
    }

    //Metodo para verificar si hay clientes en cola
    public boolean hayClientesEnCola(){
        return !colaClientes.estaVacia();
    }

    // Obtener cantidad de clientes en cola
    public int getCantidadClientesEnCola() {
        return colaClientes.getTamanio();
    }

    public boolean verificarStock(String nombreProducto, int cantidadSolicitado){
        Producto producto = buscarProducto(nombreProducto);
        if(producto == null){
            return false;
        }
        return producto.getCantidad() >= cantidadSolicitado;
    }

    public void descontarStock(String nombreProducto, int cantidad){
        Producto producto = buscarProducto(nombreProducto);
        if(producto != null){
            int nuevoStock = producto.getCantidad() - cantidad;
            producto.setCantidad(nuevoStock);
            if(nuevoStock == 0){
                eliminarProducto(nombreProducto);
                System.out.println("Producto " + nombreProducto + " agotado - Eliminado del inventario");
            }
        }
    }

    public Producto modificarProducto(String nombre, String categoria, String fecha,double precio, int cantidad, String imagen) {
        return inventario.modificarProducto(nombre, categoria, fecha, precio, cantidad, imagen);
    }
}
