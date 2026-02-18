import java.util.ArrayList;

public class Producto{
    private String nombre;
    private double precio;
    private String categoria;
    private String fechaVencimiento;
    private int cantidad;
    private ArrayList<String> listaImagenes;
    private Producto siguiente;

    public Producto(String nombre, double precio, String categoria, String fechaVencimiento, int cantidad){
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.fechaVencimiento = fechaVencimiento;
        this.cantidad = cantidad;
        listaImagenes = new ArrayList<>();
        siguiente = null;
    }

    public String getNombre(){
        return nombre;
    }
    public double getPrecio(){
        return precio;
    }
    public String getCategoria(){
        return categoria;
    }
    public String getFechaVencimiento(){
        return fechaVencimiento;
    }
    public int getCantidad(){
        return cantidad;
    }
    public ArrayList<String> getListaImagenes(){
        return listaImagenes;
    }
    public Producto getSiguiente(){
        return siguiente;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setPrecio(double precio){
        this.precio = precio;
    }
    public void setCategoria(String categoria){
        this.categoria = categoria;
    }
    public void setFechaVencimiento(String fechaVencimiento){
        this.fechaVencimiento = fechaVencimiento;
    }
    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }
    public void setSiguiente(Producto siguiente){
        this.siguiente = siguiente;
    }

    public void agregarImagenes(String rutaImagen){
        listaImagenes.add(rutaImagen);
    }
    public double calcularCostoTotal(){
        return precio * cantidad;
    }

    public String toString(){
        return "\nNombre: " + nombre +
        "\nPrecio: " + precio +
        "\nCategoria: " + categoria + 
        "\nFecha Vencimiento: " + fechaVencimiento + 
        "\nCantidad: " + cantidad + 
        "\nImagenes: " + listaImagenes;
    }
}
