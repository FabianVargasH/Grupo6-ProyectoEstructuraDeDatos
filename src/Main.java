import bl.entities.Tienda;
import bl.structures.ArbolProductos;
import ui.Menu;

import java.io.IOException;
public class Main {
    public static void main(String[] args) throws IOException {

        ArbolProductos arbol = new ArbolProductos();
        Tienda tienda = new Tienda(arbol);

        Menu.desplegar(tienda);
    }
}