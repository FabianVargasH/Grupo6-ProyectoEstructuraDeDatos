# 🛒 Proyecto APLICACIÓN DE GESTIÓN DE INVENTARIOS <img width="101" height="100" alt="Logo Cenfotec Actual (1)" src="https://github.com/user-attachments/assets/211a3278-1cf2-44f8-9ea5-f1bdff025d4f" />


## 📋 Descripción del Proyecto

Este proyecto consiste en el desarrollo de una aplicación para la **gestión de un sistema de ventas de productos en línea**, implementada en Java. El sistema utiliza **estructuras de datos dinámicas** para el control del inventario de productos y la gestión de pedidos, y ofrece al usuario una interfaz de línea de comandos (CLI) para interactuar con el sistema.

---

## 📦 Avance 1 — Lista Enlazada Simple de Productos

### ¿Qué se implementó?

En este primer avance se desarrolló la estructura central del sistema: una **lista enlazada simple** llamada `bl.structures.ListaProductos`, que permite gestionar el inventario de productos de la tienda.

### Clases implementadas

#### `bl.entities.Producto`
Representa un producto dentro del sistema. Contiene los siguientes atributos:

| Atributo           | Tipo              | Descripción                                                  |
|--------------------|-------------------|--------------------------------------------------------------|
| `nombre`           | `String`          | Nombre del producto                                          |
| `precio`           | `double`          | Precio unitario del producto                                 |
| `categoria`        | `String`          | Categoría a la que pertenece el producto                     |
| `fechaVencimiento` | `String`          | Fecha de vencimiento (opcional, aplica solo a algunos productos) |
| `cantidad`         | `int`             | Cantidad de unidades disponibles en inventario               |
| `listaImagenes`    | `ArrayList<String>` | Lista de rutas a imágenes del producto (dentro del proyecto) |
| `siguiente`        | `bl.entities.Producto`        | Atributo dedicado para el funcionamiento de la lista enlazada|


#### `bl.structures.ListaProductos`
Implementa la lista enlazada simple con los siguientes métodos:

| Método                                 | Descripción                                                                 |
|----------------------------------------|-----------------------------------------------------------------------------|
| `insertarInicio`                       | Inserta un nuevo producto al inicio de la lista                             |
| `insertarFinal`                        | Inserta un nuevo producto al final de la lista                              |
| `modificar`                            | Modifica los atributos de un producto existente                             |
| `eliminar`                             | Elimina un producto de la lista por su nombre                               |
| `buscar`                               | Busca y retorna un producto por nombre                                      |
| `mostrarLista`                         | Recorre e imprime todos los productos de la lista                           |
| `reporteCostos`                        | Imprime el costo total por producto (precio × cantidad) y el costo total acumulado de toda la lista |


#### `ui.Menu`
Contiene el menú de opciones para que el usuario pueda interactuar con el programa.

#### `Main`
Contiene el método `main()` que invoca al menú dando la entrada al programa.

---

## 🗂️ Avance 2 — Árbol Binario de Búsqueda, Cola de Prioridad y Gestión de Clientes

### ¿Qué se implementó?

En este segundo avance se reestructuró el sistema para incorporar nuevas estructuras de datos y ampliar la funcionalidad hacia la gestión completa de clientes y su proceso de compra.

### Clases implementadas

#### `bl.structures.NodoArbol`
Nodo utilizado por el árbol binario de búsqueda. Almacena un `bl.entities.Producto` y su llave (nombre), además de referencias a los nodos hijo izquierdo y derecho.

#### `bl.structures.ArbolProductos`
Implementa el inventario de la tienda como un **árbol binario de búsqueda (BST)**, usando el nombre del producto como llave. Contiene los siguientes métodos:

| Método             | Descripción                                                                 |
|--------------------|-----------------------------------------------------------------------------|
| `insertar`         | Inserta un producto en la posición correcta del árbol                       |
| `buscar`           | Busca un producto por nombre                                                |
| `eliminar`         | Elimina un producto del árbol manteniendo la estructura BST                 |
| `modificar`        | Modifica los atributos de un producto existente sin alterar la llave        |
| `mostrarInventario`| Recorre el árbol en orden e imprime todos los productos                     |
| `enOrden`          | Recorrido en orden (izquierdo, raíz, derecho)                               |
| `preOrden`         | Recorrido en pre orden (raíz, izquierdo, derecho)                           |
| `postOrden`        | Recorrido en post orden (izquierdo, derecho, raíz)                          |

#### `bl.structures.NodoLista`
Nodo utilizado por la lista enlazada del carrito. Almacena un `bl.entities.Producto` y una referencia al siguiente nodo.

#### `bl.structures.ListaProductos`
Se mantiene del avance anterior. En este avance su uso principal es como **carrito de compras** personal de cada cliente.

#### `bl.entities.Cliente`
Representa a un cliente de la tienda. Contiene los siguientes atributos:

| Atributo    | Tipo              | Descripción                                          |
|-------------|-------------------|------------------------------------------------------|
| `nombre`    | `String`          | Nombre del cliente                                   |
| `prioridad` | `int`             | Nivel de prioridad: 1 (Básico), 2 (Afiliado), 3 (Premium) |
| `carrito`   | `bl.structures.ListaProductos`  | Lista de productos seleccionados para su compra      |

Métodos principales:

| Método                  | Descripción                                              |
|-------------------------|----------------------------------------------------------|
| `agregarAlCarrito`      | Agrega una copia del producto con la cantidad deseada    |
| `calcularTotalCarrito`  | Calcula el costo total acumulado del carrito             |
| `mostrarCarrito`        | Imprime el contenido actual del carrito                  |
| `vaciarCarrito`         | Vacía el carrito tras la atención del cliente            |
| `getCantidadProductos`  | Retorna la cantidad de productos en el carrito           |

#### `bl.structures.NodoCliente`
Nodo utilizado por la cola de prioridad. Almacena un `bl.entities.Cliente` y una referencia al siguiente nodo.

#### `bl.structures.ColaClientes`
Implementa una **cola de prioridad** para gestionar el orden de atención de los clientes. El cliente con mayor prioridad es atendido primero; en caso de empate, se respeta el orden de llegada.

| Método      | Descripción                                                        |
|-------------|--------------------------------------------------------------------|
| `insertar`  | Inserta un cliente en la posición correcta según su prioridad      |
| `atender`   | Elimina y retorna el cliente al frente de la cola                  |
| `verFrente` | Retorna el cliente al frente sin eliminarlo                        |
| `estaVacia` | Verifica si la cola está vacía                                     |
| `getTamanio`| Retorna la cantidad de clientes en cola                            |

#### `bl.entities.Tienda`
Clase central del sistema que integra el inventario y la cola de clientes.

| Atributo           | Tipo              | Descripción                              |
|--------------------|-------------------|------------------------------------------|
| `inventario`       | `bl.structures.ArbolProductos`  | Árbol BST con los productos disponibles  |
| `productosAnnadidos` | `ArrayList<bl.entities.Producto>` | Lista auxiliar para el reporte de costos |
| `colaClientes`     | `bl.structures.ColaClientes`    | Cola de prioridad de clientes            |

#### `ui.Menu`
Actualizado con las siguientes opciones:

| Opción | Descripción                                                                 |
|--------|-----------------------------------------------------------------------------|
| 1      | Agregar producto al inventario                                              |
| 2      | Mostrar inventario completo                                                 |
| 3      | Buscar producto por nombre                                                  |
| 4      | Eliminar producto del inventario                                            |
| 5      | Reporte de costos del inventario                                            |
| 6      | Registrar cliente y llenar su carrito desde el inventario disponible        |
| 7      | Atender siguiente cliente e imprimir su factura con costo total             |
| 8      | Ver próximo cliente en cola sin atenderlo                                   |
| 9      | Modificar producto existente en el inventario                               |
| 10     | Salir del programa                                                          |

---

## 🗺️ Avance 3 — Grafo Ponderado y Rutas de Entrega

### ¿Qué se implementó?

En este tercer avance se incorporó un **grafo ponderado no dirigido** para la gestión de ubicaciones y el cálculo de rutas óptimas de entrega. Tanto la tienda como los clientes tienen una ubicación asociada, y el sistema calcula automáticamente el camino más corto desde la tienda hasta la ubicación del cliente utilizando el algoritmo de **Dijkstra**.

### Clases implementadas

#### `bl.structures.Arista`
Representa una conexión entre dos ubicaciones. Contiene:
- `destino`: String con el nombre de la ubicación destino
- `peso`: int con la distancia en kilómetros

#### `bl.structures.Vertice`
Clase auxiliar utilizada por el algoritmo de Dijkstra. Contiene:
- `nombre`: String con el nombre del vértice
- `distancia`: int con la distancia acumulada desde el origen

#### `bl.structures.Grafo`
Implementa el grafo ponderado no dirigido utilizando una lista de adyacencia (`Map<String, List<Arista>>`). Contiene los siguientes métodos:

| Método                | Descripción                                                                                         |
|-----------------------|-----------------------------------------------------------------------------------------------------|
| `agregarVertice`      | Agrega una nueva ubicación al grafo                                                                 |
| `agregarArista`       | Agrega una conexión bidireccional entre dos ubicaciones con un peso (distancia)                     |
| `algoritmoDijkstra`   | Calcula las distancias mínimas desde un origen a todos los vértices del grafo                       |
| `reconstruirCamino`   | Reconstruye el camino óptimo desde el origen hasta un destino usando los predecesores de Dijkstra   |
| `mostrarGrafo`        | Muestra todas las ubicaciones y sus conexiones con sus respectivas distancias                       |

#### `bl.entities.Cliente` (modificada)
Se agregó el atributo:
- `ubicacion`: String que indica la dirección o zona de entrega del cliente

Se agregaron los siguientes elementos:

| Elemento                  | Descripción                                                                               |
|---------------------------|-------------------------------------------------------------------------------------------|
| `UBICACION_TIENDA`        | Constante con la ubicación fija de la tienda (`"Almacen Central"`)                        |
| `grafo`                   | Objeto de tipo `Grafo` para gestionar el mapa de ubicaciones                              |
| `inicializarMapa()`       | Método que precarga el grafo con 6 ubicaciones y sus conexiones predeterminadas            |
| `mostrarCaminoCliente()`  | Calcula y muestra la ruta óptima desde la tienda hasta el cliente                         |
| `clienteConectado()`      | Verifica si la ubicación del cliente existe y es alcanzable desde la tienda               |
| `agregarVerticeGrafo()`   | Permite agregar nuevas ubicaciones al mapa                                                |
| `agregarAristaGrafo()`    | Permite agregar nuevas conexiones entre ubicaciones                                       |

### Ubicaciones predefinidas en el mapa

| Ubicación         | Conexiones (destino: distancia km)                                          |
|-------------------|-----------------------------------------------------------------------------|
| Almacen Central   | Barrio Norte(5), Barrio Sur(7), Zona Comercial(3), Parque Central(6)        |
| Zona Comercial    | Almacen Central(3), Barrio Sur(5), Parque Central(2)                        |
| Barrio Sur        | Almacen Central(7), Zona Comercial(5)                                       |
| Barrio Norte      | Almacen Central(5), Parque Central(4), Residencial Este(10)                 |
| Parque Central    | Almacen Central(6), Barrio Norte(4), Zona Comercial(2), Residencial Este(8) |
| Residencial Este  | Parque Central(8), Barrio Norte(10)                                         |

### Nuevas opciones del menú

| Opción | Descripción                          |
|--------|--------------------------------------|
| 9      | Mostrar mapa de ubicaciones (grafo completo) |
| 10     | Agregar nueva ubicación al mapa      |
| 11     | Agregar conexión entre ubicaciones   |

### Funcionalidades integradas

- Al agregar un cliente, se verifica si su ubicación existe en el grafo
- Si la ubicación no existe, se crea automáticamente como vértice aislado
- Se muestra la ruta óptima al momento de agregar el cliente
- Al atender un cliente, se muestra nuevamente la ruta de entrega
- Se verifica que el cliente esté conectado antes de poder ser atendido

---

## 🗂️ Estructura del Proyecto
```
Grupo6-ProyectoEstructuraDeDatos/
├── src/
│   ├── Main.java
│   ├── bl/
│   │   ├── entities/
│   │   │   ├── Producto.java
│   │   │   ├── Cliente.java
│   │   │   └── Tienda.java
│   │   └── structures/
│   │       ├── ListaProductos.java
│   │       ├── NodoLista.java
│   │       ├── ArbolProductos.java
│   │       ├── NodoArbol.java
│   │       ├── ColaClientes.java
│   │       ├── NodoCliente.java
│   │       ├── Grafo.java
│   │       ├── Arista.java
│   │       └── Vertice.java
│   └── ui/
│       └── Menu.java
└── README.md
```

---

## 👥 Integrantes del Grupo

| Nombre completo         | Correo electrónico           |
|-------------------------|------------------------------|
| Fabián Vargas Hidalgo   | fvargash@ucenfotec.ac.cr     |
| Samuel Madrigal Ugalde  | smadrigalu@ucenfotec.ac.cr   |

---

## 📚 Curso

**Estructuras de Datos | Universidad Cenfotec**  
Fecha de entrega Primer Avance: 22 de febrero  
Fecha de entrega Segundo Avance: 22 de marzo  
Fecha de entrega Tercer Avance: 26 de abril