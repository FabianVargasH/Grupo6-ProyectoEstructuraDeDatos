import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {

    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private ListaProductos lista = new ListaProductos();

    public void desplegar() throws IOException {

        int opc = 0;

        while (opc != 7) {

            System.out.println("\n=== MENU ===");
            System.out.println("1. Insertar producto");
            System.out.println("2. Mostrar productos");
            System.out.println("3. Buscar producto");
            System.out.println("4. Modificar producto");
            System.out.println("5. Eliminar producto");
            System.out.println("6. Reporte de costos");
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
                    lista.insertarFinal(nuevo);

                    System.out.println("Producto agregado correctamente.");
                    break;

                case 2:
                    lista.mostrarLista();
                    break;

                case 3:
                    System.out.print("Ingrese nombre del producto a buscar: ");
                    String productoBuscar = reader.readLine();
                    Producto productoEncontrado = lista.buscar(productoBuscar);

                    if (productoEncontrado != null) {
                        System.out.println(productoEncontrado);
                    } else {
                        System.out.println("Producto no encontrado.");
                    }
                    break;

                case 4:
                    lista.modificar();
                    break;

                case 5:
                    System.out.print("Ingrese el nombre del producto a eliminar: ");
                    String productoEliminar = reader.readLine();
                    lista.eliminar(productoEliminar);
                    break;

                case 6:
                    lista.reporteCostos();
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