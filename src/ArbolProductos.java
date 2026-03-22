public class ArbolProductos {

    NodoArbol raiz;

    public ArbolProductos() {
        this.raiz = null;
    }

    public NodoArbol getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoArbol raiz) {
        this.raiz = raiz;
    }

    public boolean estaVacia(){
        return raiz == null;
    }

    public NodoArbol buscar(String llave){

        if (estaVacia()){
            return null;
        }
        NodoArbol temp = raiz;
        while (!temp.getLlave().equalsIgnoreCase(llave)) {
            if (temp.getLlave().compareToIgnoreCase(llave) > 0) temp= temp.getIzquierdo();
            else temp = temp.getDerecho();
            if (temp == null){
                return temp;
            }
        }
        return temp;
    }

    public void insertar(Producto producto){
        NodoArbol nodo = new NodoArbol(producto);
        if (estaVacia()){
            raiz = nodo;
            return;
        }

        NodoArbol temp = raiz;
        NodoArbol padreTemp;
        while (true){
            padreTemp = temp;
            if (temp.getLlave().compareToIgnoreCase(nodo.getLlave()) > 0){
                temp = temp.getIzquierdo();
                if (temp == null){
                    padreTemp.setIzquierdo(nodo);
                    return;
                }
            } else if (temp.getLlave().compareToIgnoreCase(nodo.getLlave()) < 0){
                temp = temp.getDerecho();
                if (temp == null){
                    padreTemp.setDerecho(nodo);
                    return;
                }
            }
        }
    }

    public void enOrden(NodoArbol raizTemp){
        if(raizTemp != null){
            enOrden(raizTemp.getIzquierdo());
            System.out.print(raizTemp.getLlave() + " ");
            enOrden(raizTemp.getDerecho());
        }

    }

    public void preOrden(NodoArbol raizTemp){
        if(raizTemp != null){
            System.out.print(raizTemp.getLlave() + " ");
            preOrden(raizTemp.getIzquierdo());
            preOrden(raizTemp.getDerecho());
        }
    }

    public void postOrden(NodoArbol raizTemp){
        if(raizTemp != null){
            postOrden(raizTemp.getIzquierdo());
            postOrden(raizTemp.getDerecho());
            System.out.print(raizTemp.getLlave() + " ");
        }

    }

    private NodoArbol getSucesor(NodoArbol nodo){
        NodoArbol padreSucesor = nodo;
        NodoArbol sucesor = nodo;
        NodoArbol temp = nodo.getDerecho();
        while (temp != null){
            padreSucesor = sucesor;
            sucesor = temp;
            temp = temp.getIzquierdo();
        }
        if (sucesor != nodo.getDerecho()){
            padreSucesor.setIzquierdo(sucesor.getDerecho());
            sucesor.setDerecho(nodo.getDerecho());
        }
        return sucesor;
    }


    private NodoArbol getPadre(String llave){
        NodoArbol padreTemp = raiz;
        NodoArbol temp = raiz;

        while (!temp.getLlave().equalsIgnoreCase(llave)) {
            padreTemp = temp;
            if (temp.getLlave().compareToIgnoreCase(llave) > 0)temp= temp.getIzquierdo();
            else temp = temp.getDerecho();
        }
        return padreTemp;
    }

    public NodoArbol eliminar(String llave){

        if (estaVacia()){
            return null;
        }
        NodoArbol nodo = buscar(llave);

        if (nodo == null) return null;

        if(nodo == raiz){
            if(nodo.getIzquierdo() == null && nodo.getDerecho() == null) raiz = null;
            else if(nodo.getIzquierdo() == null) raiz = raiz.getDerecho();
            else if(nodo.getDerecho() == null) raiz = raiz.getIzquierdo();
            else {
                NodoArbol sucesor = getSucesor(raiz);
                sucesor.setIzquierdo(raiz.getIzquierdo());
                raiz = sucesor;
            }
            return nodo;
        }

        NodoArbol padre = getPadre(llave);

        if(nodo.getIzquierdo() == null && nodo.getDerecho() == null){

            if(nodo == padre.getIzquierdo()) padre.setIzquierdo(null);
            else padre.setDerecho(null);

        } else if(nodo.getDerecho() == null){

            if (nodo == padre.getIzquierdo()) padre.setIzquierdo(nodo.getIzquierdo());
            else padre.setDerecho(nodo.getIzquierdo());

        } else if(nodo.getIzquierdo() == null){

            if (nodo == padre.getIzquierdo()) padre.setIzquierdo(nodo.getDerecho());
            else padre.setDerecho(nodo.getDerecho());

        } else{
            NodoArbol sucesor = getSucesor(nodo);
            sucesor.setIzquierdo(nodo.getIzquierdo());
            if(nodo == padre.getIzquierdo()) padre.setIzquierdo(sucesor);
            else padre.setDerecho(sucesor);

        }
        return nodo;
    }

    // metodo para mostrar inventario completo
    public void mostrarInventario(NodoArbol raizTemp){
        if(raizTemp != null){
            mostrarInventario(raizTemp.getIzquierdo());
            System.out.println(raizTemp.getNodo());
            System.out.println("- - - - - - - - - - - - - - - -");
            mostrarInventario(raizTemp.getDerecho());
        }
    }
    //Metodo para modificar un producto
    public Producto modificarProducto(String nombre, String nuevaCategoria, String nuevaFecha, double nuevoPrecio, int nuevaCantidad, String nuevaImagen) {
        NodoArbol nodo = buscar(nombre);
        if (nodo == null) return null;
        Producto p = nodo.getNodo();
        p.setCategoria(nuevaCategoria);
        p.setFechaVencimiento(nuevaFecha);
        if (nuevoPrecio > 0) p.setPrecio(nuevoPrecio);
        if (nuevaCantidad >= 0) p.setCantidad(nuevaCantidad);
        if (nuevaImagen != null && !nuevaImagen.isEmpty()) p.agregarImagenes(nuevaImagen);
        return p;
    }



}
