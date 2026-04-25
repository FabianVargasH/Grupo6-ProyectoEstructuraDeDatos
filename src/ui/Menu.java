package ui;

import bl.entities.Cliente;
import bl.entities.Producto;
import bl.entities.Tienda;
import bl.structures.NodoLista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void desplegar(Tienda tienda)throws IOException{
        int opc = 0;
        while(opc != 10){
            System.out.println("\n=== TIENDA ===");
            System.out.println("1. Agregar producto al inventario");
            System.out.println("2. Mostrar inventario");
            System.out.println("3. Buscar producto");
            System.out.println("4. Eliminar producto");
            System.out.println("5. Reporte de costos");
            System.out.println("6. Registrar cliente");
            System.out.println("7. Atender cliente");
            System.out.println("8. Ver próximo cliente");
            System.out.println("9. Modificar producto");
            System.out.println("10. Salir");
            System.out.print("Seleccione una opción: ");

            opc = Integer.parseInt(reader.readLine());
            switch (opc){
                case 1:
                    agregarProducto(tienda);
                    break;
                case 2:
                    tienda.mostrarInventario();
                    break;
                case 3:
                    buscarProducto(tienda);
                    break;
                case 4:
                    eliminarProducto(tienda);
                    break;
                case 5:
                    tienda.reporteCostos();
                    break;
                case 6:
                    registrarCliente(tienda);
                    break;
                case 7:
                    atenderCliente(tienda);
                    break;
                case 8:
                    verProximoCliente(tienda);
                    break;
                case 9:
                    modificarProducto(tienda);
                    break;
                case 10:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opcion no válida");
            }
        }
    }

    private static void agregarProducto(Tienda tienda) throws IOException{
        System.out.println("\n=== Insertar bl.entities.Producto === ");
        System.out.print("Ingrese el nombre: ");
        String nombre = reader.readLine();
        System.out.print("Ingrese el precio: ");
        double precio = Double.parseDouble(reader.readLine());
        System.out.print("Ingrese la categoria: ");
        String categoria = reader.readLine();
        System.out.print("Ingrese la fecha de vencimiento: ");
        String fecha = reader.readLine();
        System.out.print("Ingrese la cantidad: ");
        int cantidad = Integer.parseInt(reader.readLine());
        Producto nuevo = new Producto(nombre, precio, categoria, fecha, cantidad);
        System.out.print("¿Desea agregar imagenes? (s/n): ");
        String opcion = reader.readLine();
        while (opcion.equalsIgnoreCase("s")) {
            System.out.print("Ruta de la imagen: ");
            String ruta = reader.readLine();
            nuevo.agregarImagenes(ruta);

            System.out.print("¿Agregar otra imagen? (s/n): ");
            opcion = reader.readLine();
        }
        tienda.agregarProducto(nuevo);
    }

    private static void buscarProducto(Tienda tienda) throws IOException{
        if(tienda.getProductosAnnadidos().isEmpty()){
            System.out.println("\nEl inventario está vacío");
            return;
        }
        System.out.print("Ingrese nombre del producto a buscar: ");
        String productoBuscar = reader.readLine();
        Producto productoEncontrado = tienda.buscarProducto(productoBuscar);
        if(productoEncontrado != null){
            System.out.println("\n-- bl.entities.Producto Encontrado --");
            System.out.println(productoEncontrado);
        }else{
            System.out.println("\nLo sentimos, el producto no se encuentra en el inventario");
        }
    }

    private static void eliminarProducto(Tienda tienda)throws IOException{
        if(tienda.getProductosAnnadidos().isEmpty()){
            System.out.println("\nEl inventario está vacío");
            return;
        }
        System.out.print("Ingrese el nombre del producto a eliminar: ");
        String productoEliminar = reader.readLine();
        Producto productoEliminado = tienda.eliminarProducto(productoEliminar);
        if(productoEliminado != null){
            System.out.println("\nbl.entities.Producto eliminado: " + productoEliminado.getNombre());
        }else{
            System.out.println("\nEl producto no se encuentra en nuestro inventario");
        }
    }

    private static void registrarCliente(Tienda tienda)throws IOException{
        System.out.println("\n=== Registrar bl.entities.Cliente ===");
        System.out.print("Nombre del cliente: ");
        String nombre = reader.readLine();
        System.out.println("\nPrioridad");
        System.out.println("1. Básico");
        System.out.println("2. Afiliado");
        System.out.println("3. Premium");
        int prioridad = 0;
        while(prioridad <1 || prioridad > 3){
            System.out.println("Seleccione (1-3): ");
            try {
                prioridad = Integer.parseInt(reader.readLine());
                if (prioridad < 1 || prioridad > 3) {
                    System.out.println("Opción inválida, intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un número válido.");
            }
        }
        Cliente nuevoCliente = new Cliente(nombre,prioridad);
        System.out.println("\n-- Llenado de carrito --");
        boolean comprando = true;
        while(comprando){
            System.out.println("\nProductos disponibles:");
            tienda.mostrarInventario();
            System.out.println("\nNombre del producto (o escriba 'fin' para terminar su compra): ");
            String nombreProducto = reader.readLine().trim();
            if(nombreProducto.equalsIgnoreCase("fin")){
                comprando = false;
                continue;
            }
            Producto producto = tienda.buscarProducto(nombreProducto);
            if(producto == null){
                System.out.println("bl.entities.Producto no encontrado");
                continue;
            }
            System.out.println("Cantidad deseada (Stock: " +producto.getCantidad() + "): ");
            int cantidad = Integer.parseInt(reader.readLine());

            if(tienda.verificarStock(nombreProducto, cantidad)){
                nuevoCliente.agregarAlCarrito(producto,cantidad);
                tienda.descontarStock(nombreProducto, cantidad);
                System.out.println("bl.entities.Producto agregado al carrito exitosamente");
                nuevoCliente.mostrarCarrito();
            }else{
                System.out.println("Stock insuficiente. Solo hay " + producto.getCantidad() +" unidades");
            }
        }
        if(nuevoCliente.getCantidadProductos()>0){
            tienda.agregarClienteCola(nuevoCliente);
            System.out.println("bl.entities.Cliente registrado y agregado a la cola");
            System.out.println("Total: " + nuevoCliente.calcularTotalCarrito());
        }else{
            System.out.println("\nbl.entities.Cliente no agregado - carrito vacio");
        }
    }

    private static void atenderCliente(Tienda tienda){
        System.out.println("\n=== Atender bl.entities.Cliente ===");
        if(!tienda.hayClientesEnCola()){
            System.out.println("No hay clientes en la cola");
            return;
        }
        Cliente cliente = tienda.atenderSiguienteCliente();
        if(cliente != null){
            System.out.println("--- Factura ---");
            System.out.println("bl.entities.Cliente: " + cliente.getNombre());
            String prioridad;
            switch (cliente.getPrioridad()) {
                case 1:
                    prioridad = "Básico";
                    break;
                case 2:
                    prioridad = "Afiliado";
                    break;
                case 3:
                    prioridad = "Premium";
                    break;
                default:
                    prioridad = "";
            }
            System.out.println("Tipo: " +prioridad);
            System.out.println("-----------------------------------");
            NodoLista actual = cliente.getCarrito().getPrimero();
            int item = 1;
            double total = 0;
            while(actual != null){
                Producto producto = actual.getNodo();
                double subtotal = producto.calcularCostoTotal();
                total += subtotal;

                System.out.println(item++ + ". " + producto.getNombre());
                System.out.println(" " + producto.getCantidad() + " x " + producto.getPrecio() + " = " + subtotal);
                actual = actual.getSiguiente();
            }
            System.out.println("-----------------------------------");
            System.out.println("Total: " + total);
            cliente.vaciarCarrito();
        }
    }

    private static void verProximoCliente(Tienda tienda){
        System.out.println("=== Proximo bl.entities.Cliente ===");
        if(!tienda.hayClientesEnCola()){
            System.out.println("No hay clientes en la cola");
            return;
        }
        Cliente proximo = tienda.verProximoCliente();
        System.out.println("bl.entities.Cliente: " + proximo.getNombre());
        System.out.println("Prioridad: " +proximo.getPrioridad());
        System.out.println("Productos: " + proximo.getCantidadProductos());
        System.out.println("Total: " + proximo.calcularTotalCarrito());
    }

    //Metodo para modificar producto del inventario
    private static void modificarProducto(Tienda tienda) throws IOException {
        if (tienda.getProductosAnnadidos().isEmpty()) {
            System.out.println("\nEl inventario está vacío");
            return;
        }
        System.out.print("Ingrese el nombre del producto a modificar: ");
        String nombre = reader.readLine();

        Producto actual = tienda.buscarProducto(nombre);
        if (actual == null) {
            System.out.println("bl.entities.Producto no encontrado");
            return;
        }

        System.out.println("\nDatos actuales:");
        System.out.println(actual);

        System.out.print("Nueva categoría (actual: " + actual.getCategoria() + "): ");
        String categoria = reader.readLine();
        System.out.print("Nueva fecha de vencimiento (actual: " + actual.getFechaVencimiento() + "): ");
        String fecha = reader.readLine();
        System.out.print("Nuevo precio (actual: " + actual.getPrecio() + "): ");
        double precio = Double.parseDouble(reader.readLine());
        System.out.print("Nueva cantidad (actual: " + actual.getCantidad() + "): ");
        int cantidad = Integer.parseInt(reader.readLine());
        System.out.print("Agregar imagen? (s/n): ");
        String opcion = reader.readLine();
        String imagen = "";
        if (opcion.equalsIgnoreCase("s")) {
            System.out.print("Ruta de la imagen: ");
            imagen = reader.readLine();
        }

        tienda.modificarProducto(nombre, categoria, fecha, precio, cantidad, imagen);
        System.out.println("bl.entities.Producto modificado exitosamente");
    }
}