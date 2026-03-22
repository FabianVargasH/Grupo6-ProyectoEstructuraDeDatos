import java.io.IOException;
public class Main {
    public static void main(String[] args) throws IOException {
        ArbolProductos arbol = new ArbolProductos();
        Tienda tienda = new Tienda(arbol);
        Menu menu = new Menu();
        menu.desplegar(tienda);
    }
}