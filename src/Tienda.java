import java.util.ArrayList;

public class Tienda {

    private ArbolProductos inventario;
    private ArrayList<Producto> productosAnnadidos = new ArrayList<>();

    public Tienda(ArbolProductos inventario) {
        this.inventario = inventario;
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
            System.out.println("\nError: Lo sentimos, el inventario está vacio.");
        } else {
            inventario.enOrden(inventario.getRaiz());
        }
    }

    public void reporteCostos(){

        if (inventario.estaVacia()) {
            System.out.println("\n Error: Lo sentimos, el inventario está vacio.");
        } else {
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
}
