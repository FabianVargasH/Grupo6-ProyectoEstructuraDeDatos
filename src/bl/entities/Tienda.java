package bl.entities;

import bl.structures.ArbolProductos;
import bl.structures.ColaClientes;
import bl.structures.Grafo;
import bl.structures.NodoArbol;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Tienda {

    private ArbolProductos inventario;
    private ArrayList<Producto> productosAnnadidos = new ArrayList<>();
    private ColaClientes colaClientes;
    private Grafo grafo;
    private final String ubicacion;

    public static final String UBICACION_TIENDA = "Almacén Central";

    public Tienda(ArbolProductos inventario) {
        this.inventario = inventario;
        this.productosAnnadidos = new ArrayList<>();
        this.colaClientes = new ColaClientes();
        this.ubicacion = UBICACION_TIENDA;
        this.grafo = new Grafo();
        inicializarMapa();
    }

    // ---------------------------------------------------------------
    // vértices y aristas preinsertados
    // ---------------------------------------------------------------
    private void inicializarMapa() {

        // La tienda siempre es el nodo raíz del mapa
        grafo.agregarVertice(UBICACION_TIENDA);

        // Ubicaciones vecinas directas del almacen
        grafo.agregarArista(UBICACION_TIENDA, "Barrio Norte",   5);
        grafo.agregarArista(UBICACION_TIENDA, "Barrio Sur",     7);
        grafo.agregarArista(UBICACION_TIENDA, "Zona Comercial", 3);
        grafo.agregarArista(UBICACION_TIENDA, "Parque Central", 6);

        // Conexiones entre ubicaciones
        grafo.agregarArista("Barrio Norte",   "Parque Central", 4);
        grafo.agregarArista("Barrio Sur",     "Zona Comercial", 5);
        grafo.agregarArista("Zona Comercial", "Parque Central", 2);
        grafo.agregarArista("Parque Central", "Residencial Este", 8);
        grafo.agregarArista("Barrio Norte",   "Residencial Este", 10);
    }


    public ColaClientes getColaClientes(){ return colaClientes; }
    public ArrayList<Producto> getProductosAnnadidos(){ return productosAnnadidos; }
    public Grafo getGrafo(){ return grafo; }
    public String getUbicacion(){ return ubicacion; }


    public void agregarVerticeGrafo(String vertice){
        grafo.agregarVertice(vertice);
        System.out.println("Vertice '" + vertice + "' agregado al mapa.");
    }

    public void agregarAristaGrafo(String origen, String destino, int peso){
        grafo.agregarArista(origen, destino, peso);
        System.out.println("Arista agregada: " + origen + " <-> " + destino + " (distancia: " + peso + ")");
    }

    public void mostrarMapa(){
        System.out.println("\n=== MAPA DE UBICACIONES ===");
        grafo.mostrarGrafo();
    }

    public void mostrarCaminoCliente(Cliente cliente){
        String destino = cliente.getUbicacion();

        Map<String, Integer> distancias   = new HashMap<>();
        Map<String, String>  predecesores = new HashMap<>();

        grafo.algoritmoDijkstra(UBICACION_TIENDA, distancias, predecesores);

        List<String> camino = grafo.reconstruirCamino(UBICACION_TIENDA, destino, predecesores);

        System.out.println("\n=== RUTA DE ENTREGA ===");
        if (camino.isEmpty()) {
            System.out.println("No existe camino entre '" + UBICACION_TIENDA + "' y '" + destino + "'.");
        } else {
            System.out.println("Origen  : " + UBICACION_TIENDA);
            System.out.println("Destino : " + destino);
            System.out.print("Camino  : ");
            for (int i = 0; i < camino.size(); i++){
                System.out.print(camino.get(i));
                if (i < camino.size() - 1) System.out.print(" → ");
            }
            System.out.println();
            System.out.println("Distancia total: " + distancias.get(destino) + " km");
        }
    }


    public boolean clienteConectado(Cliente cliente){
        String destino = cliente.getUbicacion();

        Map<String, Integer> distancias   = new HashMap<>();
        Map<String, String>  predecesores = new HashMap<>();

        grafo.algoritmoDijkstra(UBICACION_TIENDA, distancias, predecesores);

        // El vértice del cliente debe existir Y ser alcanzable (distancia != MAX_VALUE)
        if (!distancias.containsKey(destino)) return false;
        return distancias.get(destino) != Integer.MAX_VALUE;
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
            for (Producto temp : productosAnnadidos) {
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
        // Al insertar el cliente, su ubicación se agrega como vértice al grafo
        grafo.agregarVertice(cliente.getUbicacion());
        colaClientes.insertar(cliente);
    }

    public Cliente atenderSiguienteCliente()
    {
        return colaClientes.atender();
    }
    public Cliente verProximoCliente(){
        return colaClientes.verFrente();
    }
    public boolean hayClientesEnCola(){
        return !colaClientes.estaVacia();
    }
    public int getCantidadClientesEnCola(){
        return colaClientes.getTamanio();
    }

    public boolean verificarStock(String nombreProducto, int cantidadSolicitado){
        Producto producto = buscarProducto(nombreProducto);
        if(producto == null) return false;
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

    public Producto modificarProducto(String nombre, String categoria, String fecha, double precio, int cantidad, String imagen) {
        return inventario.modificarProducto(nombre, categoria, fecha, precio, cantidad, imagen);
    }
}