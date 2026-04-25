package ui;

import bl.entities.Cliente;
import bl.entities.Producto;
import bl.entities.Tienda;
import bl.structures.ArbolProductos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {
    private Tienda tienda;
    private BufferedReader br;

    public Menu() {
        this.tienda = new Tienda(new ArbolProductos());
        this.br = new BufferedReader(new InputStreamReader(System.in));
    }

    public void mostrarMenuPrincipal() throws IOException {
        int opcion;
        do {
            System.out.println("\n=== SISTEMA DE GESTION DE VENTAS ===");
            System.out.println("Tienda ubicada en: " + Tienda.UBICACION_TIENDA);
            System.out.println("1. Agregar producto al inventario");
            System.out.println("2. Mostrar inventario");
            System.out.println("3. Modificar producto");
            System.out.println("4. Eliminar producto");
            System.out.println("5. Reporte de costos del inventario");
            System.out.println("6. Agregar cliente a la cola (con carrito)");
            System.out.println("7. Atender siguiente cliente");
            System.out.println("8. Mostrar cola de clientes");
            System.out.println("9. Mostrar mapa de ubicaciones");
            System.out.println("10. Agregar nueva ubicacion al mapa");
            System.out.println("11. Agregar conexion entre ubicaciones");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1:
                    agregarProducto();
                    break;
                case 2:
                    mostrarInventario();
                    break;
                case 3:
                    modificarProducto();
                    break;
                case 4:
                    eliminarProducto();
                    break;
                case 5:
                    reporteCostosInventario();
                    break;
                case 6:
                    agregarCliente();
                    break;
                case 7:
                    atenderCliente();
                    break;
                case 8:
                    mostrarColaClientes();
                    break;
                case 9:
                    mostrarMapa();
                    break;
                case 10:
                    agregarUbicacion();
                    break;
                case 11:
                    agregarConexion();
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (opcion != 0);
    }

    //Metodos sobre productos
    private void agregarProducto() throws IOException {
        System.out.println("\n--- AGREGAR PRODUCTO ---");

        System.out.print("Nombre: ");
        String nombre = br.readLine();

        if (tienda.buscarProducto(nombre) != null) {
            System.out.println("Error: Ya existe un producto con ese nombre.");
            return;
        }

        System.out.print("Precio: ");
        double precio = leerDouble();

        System.out.print("Categoria: ");
        String categoria = br.readLine();

        System.out.print("Fecha de vencimiento (YYYY-MM-DD): ");
        String fechaVencimiento = br.readLine();

        System.out.print("Cantidad en stock: ");
        int cantidad = leerEntero();

        Producto nuevoProducto = new Producto(nombre, precio, categoria, fechaVencimiento, cantidad);

        System.out.print("Desea agregar una imagen? (s/n): ");
        String respuesta = br.readLine();
        if (respuesta.equalsIgnoreCase("s")) {
            System.out.print("Ruta de la imagen: ");
            String ruta = br.readLine();
            nuevoProducto.agregarImagenes(ruta);
        }

        tienda.agregarProducto(nuevoProducto);
    }

    private void mostrarInventario() {
        tienda.mostrarInventario();
    }

    private void modificarProducto() throws IOException {
        System.out.println("\n--- MODIFICAR PRODUCTO ---");
        System.out.print("Nombre del producto a modificar: ");
        String nombre = br.readLine();

        Producto producto = tienda.buscarProducto(nombre);
        if (producto == null) {
            System.out.println("Producto no encontrado.");
            return;
        }

        System.out.println("Deje en blanco para no modificar el campo.\n");

        System.out.print("Nueva categoria [" + producto.getCategoria() + "]: ");
        String categoria = br.readLine();
        if (categoria.isEmpty()) categoria = producto.getCategoria();

        System.out.print("Nueva fecha vencimiento [" + producto.getFechaVencimiento() + "]: ");
        String fecha = br.readLine();
        if (fecha.isEmpty()) fecha = producto.getFechaVencimiento();

        System.out.print("Nuevo precio [" + producto.getPrecio() + "]: ");
        String precioStr = br.readLine();
        double precio;
        if (precioStr.isEmpty()) {
            precio = producto.getPrecio();
            System.out.println("Manteniendo precio actual: " + precio);
        } else {
            precio = Double.parseDouble(precioStr);
        }

        System.out.print("Nueva cantidad [" + producto.getCantidad() + "]: ");
        String cantidadStr = br.readLine();
        int cantidad;
        if (cantidadStr.isEmpty()) {
            cantidad = producto.getCantidad();
        } else {
            cantidad = Integer.parseInt(cantidadStr);
        }

        System.out.print("Nueva imagen (ruta) - Enter para omitir: ");
        String imagen = br.readLine();
        if (imagen.isEmpty()) imagen = null;

        Producto modificado = tienda.modificarProducto(nombre, categoria, fecha, precio, cantidad, imagen);
        if (modificado != null) {
            System.out.println("Producto modificado exitosamente.");
        }
    }

    private void eliminarProducto() throws IOException {
        System.out.println("\n--- ELIMINAR PRODUCTO ---");
        System.out.print("Nombre del producto a eliminar: ");
        String nombre = br.readLine();

        Producto eliminado = tienda.eliminarProducto(nombre);
        if (eliminado != null) {
            System.out.println("Producto '" + nombre + "' eliminado del inventario.");
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    private void reporteCostosInventario() {
        tienda.reporteCostos();
    }

    //Metodos sobre clientes

    private void agregarCliente() throws IOException {
        System.out.println("\n--- AGREGAR CLIENTE ---");

        System.out.print("Nombre del cliente: ");
        String nombre = br.readLine();

        System.out.println("\nPrioridad:");
        System.out.println("1 - Basico");
        System.out.println("2 - Afiliado");
        System.out.println("3 - Premium");
        System.out.print("Seleccione prioridad (1-3): ");
        int prioridad = leerEntero();
        while (prioridad < 1 || prioridad > 3) {
            System.out.print("Prioridad invalida. Seleccione 1, 2 o 3: ");
            prioridad = leerEntero();
        }

        System.out.println("\nUbicaciones disponibles en el mapa:");
        //Ciclo para poder imprimir todas las ubicaciones desde el grafo
        for (String ubicacion : tienda.getGrafo().getListaAdyacencia().keySet()) {
            if (ubicacion.equals(Tienda.UBICACION_TIENDA)) {
                System.out.println("   - " + ubicacion + " (Tienda)");
            } else {
                System.out.println("   - " + ubicacion);
            }
        }
        System.out.print("Ingrese la ubicacion del cliente: ");
        String ubicacion = br.readLine();

        Cliente cliente = new Cliente(nombre, prioridad, ubicacion);

        System.out.println("\n--- LLENAR CARRITO PARA " + nombre.toUpperCase() + " ---");
        boolean agregando = true;
        while (agregando) {
            System.out.println("\nProductos disponibles:");
            mostrarInventarioResumido();

            System.out.print("Nombre del producto a agregar (o 'fin' para terminar): ");
            String nombreProducto = br.readLine();
            if (nombreProducto.equalsIgnoreCase("fin")) {
                agregando = false;
                continue;
            }

            Producto producto = tienda.buscarProducto(nombreProducto);
            if (producto == null) {
                System.out.println("Producto no encontrado.");
                continue;
            }

            System.out.print("Cantidad disponible: " + producto.getCantidad() + ". Cuantos desea? ");
            int cantidad = leerEntero();

            if (cantidad <= 0) {
                System.out.println("Cantidad invalida.");
                continue;
            }

            if (!tienda.verificarStock(nombreProducto, cantidad)) {
                System.out.println("Stock insuficiente. Solo hay " + producto.getCantidad() + " unidades.");
                continue;
            }

            cliente.agregarAlCarrito(producto, cantidad);
            tienda.descontarStock(nombreProducto, cantidad);
            System.out.println("Producto agregado al carrito.");
        }

        if (cliente.getCantidadProductos() > 0) {
            tienda.agregarClienteCola(cliente);
            System.out.println("\nCliente agregado a la cola con " + cliente.getCantidadProductos() + " productos.");
            tienda.mostrarCaminoCliente(cliente);
        } else {
            System.out.println("\nCliente no agregado porque el carrito esta vacio.");
        }
    }

    private void mostrarInventarioResumido() {
        java.util.ArrayList<Producto> productos = tienda.getProductosAnnadidos();
        if (productos.isEmpty()) {
            System.out.println("   No hay productos en inventario.");
        } else {
            for (Producto p : productos) {
                System.out.println("   - " + p.getNombre() + " (Stock: " + p.getCantidad() + ", Precio: $" + p.getPrecio() + ")");
            }
        }
    }

    private void atenderCliente() throws IOException {
        System.out.println("\n--- ATENDER CLIENTE ---");

        if (!tienda.hayClientesEnCola()) {
            System.out.println("No hay clientes en espera.");
            return;
        }

        Cliente atendido = tienda.atenderSiguienteCliente();
        if (atendido == null) {
            System.out.println("No se pudo atender al cliente por ubicación desconectada.");
            return;
        }
        System.out.println("\n=== FACTURA ===");
        System.out.println("Cliente: " + atendido.getNombre());
        System.out.println("Ubicacion de entrega: " + atendido.getUbicacion());
        System.out.println("\n--- Productos comprados ---");
        atendido.mostrarCarrito();
        System.out.println("\nTOTAL A PAGAR: " + atendido.calcularTotalCarrito());

        System.out.println("==================\n");
    }

    private void mostrarColaClientes() {
        System.out.println("\n--- CLIENTES EN ESPERA ---");
        if (!tienda.hayClientesEnCola()) {
            System.out.println("No hay clientes en la cola.");
            return;
        }

        System.out.println("Cantidad de clientes en espera: " + tienda.getCantidadClientesEnCola());
        Cliente proximo = tienda.verProximoCliente();
        if (proximo != null) {
            System.out.println("Proximo a atender: " + proximo.getNombre());
        }
    }
    // Metodos del grafo de ubicaciones
    private void mostrarMapa() {
        System.out.println("\n--- MAPA DE UBICACIONES ---");
        tienda.mostrarMapa();
    }

    private void agregarUbicacion() throws IOException {
        System.out.println("\n--- AGREGAR NUEVA UBICACION ---");
        System.out.print("Nombre de la nueva ubicacion: ");
        String ubicacion = br.readLine();
        tienda.agregarVerticeGrafo(ubicacion);
    }

    private void agregarConexion() throws IOException {
        System.out.println("\n--- AGREGAR CONEXION ENTRE UBICACIONES ---");
        System.out.print("Ubicacion origen: ");
        String origen = br.readLine();
        System.out.print("Ubicacion destino: ");
        String destino = br.readLine();
        System.out.print("Distancia (km): ");
        int distancia = leerEntero();

        tienda.agregarAristaGrafo(origen, destino, distancia);
    }

    //Metodos auxiliar para validar ingreso de datos
    private int leerEntero() throws IOException {
        while (true) {
            try {
                return Integer.parseInt(br.readLine());
            } catch (NumberFormatException e) {
                System.out.print("Entrada invalida. Ingrese un numero entero: ");
            }
        }
    }
    private double leerDouble() throws IOException {
        while (true) {
            try {
                return Double.parseDouble(br.readLine());
            } catch (NumberFormatException e) {
                System.out.print("Entrada invalida. Ingrese un numero: ");
            }
        }
    }
}