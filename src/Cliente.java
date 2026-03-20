public class Cliente {
    private String nombre;
    private int prioridad;
    private ListaProductos carrito;

    public Cliente(){}

    public Cliente(String nombre, int prioridad){
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.carrito = new ListaProductos();
    }

    //Getters
    public String getNombre(){
        return nombre;
    }
    public int getPrioridad(){
        return prioridad;
    }
    public ListaProductos getCarrito(){
        return carrito;
    }

    //Setters
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setPrioridad(int prioridad){
        if(prioridad >= 1 && prioridad <= 3){
            this.prioridad = prioridad;
        }
    }

    //Metodo para agregar producto al carrito
    public void agregarAlCarrito(Producto producto, int cantidad){
        //Se crea una copia del producto con la cantidad deseada
        Producto productoCarrito = new Producto(
                producto.getNombre(),
                producto.getPrecio(),
                producto.getCategoria(),
                producto.getFechaVencimiento(),
                cantidad
        );
        carrito.insertarFinal(productoCarrito);
    }

    //Metodo para calcular total del carrito
    public double calcularTotalCarrito(){
        double total = 0;
        NodoLista temp = carrito.getPrimero();

        while(temp !=null){
            total += temp.getNodo().calcularCostoTotal();
            temp = temp.getSiguiente();
        }
        return total;
    }

    //Metodo para mostrar el contenido del carrito
    public void mostrarCarrito(){
        System.out.println("\n--- Carrito de " + nombre + " ---");
        carrito.mostrarLista();
    }

    //Metodo para vaciar el contenido del carrito
    public void vaciarCarrito(){
        carrito.vaciarLista();
        System.out.println("Carrito vaciado correctamente");
    }

    //Metodo para obtener la cantidad de productos en carrito
    public int getCantidadProductos(){
        int contador = 0;
        NodoLista temp = carrito.getPrimero();
        while(temp !=null){
            contador++;
            temp = temp.getSiguiente();
        }
        return contador;
    }

    public String toString(){
        String tipoPrioridad = "";
        switch (prioridad){
            case 1:
                tipoPrioridad = "Basico";
                break;
            case 2:
                tipoPrioridad = "Afiliado";
                break;
            case 3:
                tipoPrioridad = "Premium";
                break;
        }
        return "\nCliente: " + nombre + "\nPrioridad: " + tipoPrioridad + "\nProductos: " + getCantidadProductos();
    }
}
