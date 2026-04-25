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

## 🗂️ Estructura del Proyecto
```
Grup6-ProyectoEstructuraDeDatos/
├── src/
│   ├── Main.java
│   ├── bl.entities.Producto.java
│   ├── bl.structures.NodoArbol.java
│   ├── bl.structures.ArbolProductos.java
│   ├── bl.structures.NodoLista.java
│   ├── bl.structures.ListaProductos.java
│   ├── bl.entities.Cliente.java
│   ├── bl.structures.NodoCliente.java
│   ├── bl.structures.ColaClientes.java
│   ├── bl.entities.Tienda.java
│   └── ui.Menu.java
├── imagenes/
│   └── (imágenes de los productos)
└── README.md
└── .gitignore
```

---

## ▶️ ¿Cómo ejecutar el proyecto?

1. Clonar o descargar el repositorio.
2. Abrir el proyecto en un IDE.
3. Compilar y ejecutar la clase `Main.java`.
4. Interactuar con el sistema a través del menú de consola.

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
