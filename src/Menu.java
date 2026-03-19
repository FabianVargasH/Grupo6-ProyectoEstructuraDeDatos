import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {

    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private ArbolProductos lista = new ArbolProductos();
    private Tienda tienda = new Tienda(lista);

    public void desplegar() throws IOException {

        int opc = 0;

        while (opc != 7) {

            System.out.println("\n=== TIENDA ===");
            System.out.println("1. Agregar producto al inventario");
            System.out.println("2. Mostrar inventario");
            System.out.println("3. Buscar producto");
            System.out.println("4. Eliminar producto");
            System.out.println("5. Reporte de costos");
            System.out.println("6. Seleccionar producto (Para el carrito)");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opcion: ");

            opc = Integer.parseInt(reader.readLine());

            switch (opc) {

                case 1:
                    System.out.println("=== Insertar Producto ===");

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
                    tienda.agregarProducto(nuevo);

                    break;

                case 2:
                    tienda.mostrarInventario();
                    break;

                case 3:

                    if (tienda.getProductosAnnadidos().isEmpty()){
                        System.out.println("\nError: Lo sentimos, el inventario está vacio.");
                        break;
                    }

                    System.out.print("Ingrese nombre del producto a buscar: ");
                    String productoBuscar = reader.readLine();

                    Producto productoEncontrado = tienda.buscarProducto(productoBuscar);

                    if (productoEncontrado != null) System.out.println(productoEncontrado);
                    else {
                        System.out.println("\nError: Lo sentimos, el producto no se encuentra en el inventario.");
                    }

                    break;

                case 4:

                    if (tienda.getProductosAnnadidos().isEmpty()){
                        System.out.println("\nError: Lo sentimos, el inventario está vacio.");
                        break;
                    }

                    System.out.print("Ingrese el nombre del producto a eliminar: ");
                    String productoEliminar = reader.readLine();


                    Producto productoEliminado = tienda.eliminarProducto(productoEliminar);
                    if (productoEliminado != null) System.out.println("\nProducto eliminado: " + productoEliminado);
                    else {
                        System.out.println("\nError: Lo sentimos, el producto no se encuentra en el inventario.");
                    }

                    break;

                case 5:
                    tienda.reporteCostos();

                    break;

                case 6:
                     //PRODUCTO PARA EL CARRITO

                     break;

                case 7:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opcion no valida");
            }
        }
    }
}