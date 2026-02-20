# 🛒 Proyecto APLICACIÓN DE GESTIÓN DE INVENTARIOS

## 📋 Descripción del Proyecto

Este proyecto consiste en el desarrollo de una aplicación para la **gestión de un sistema de ventas de productos en línea**, implementada en Java. El sistema utiliza **estructuras de datos dinámicas** para el control del inventario de productos y la gestión de pedidos, y ofrece al usuario una interfaz de línea de comandos (CLI) para interactuar con el sistema tanto desde el rol de administrador como de cliente.

---

## 📦 Avance 1 — Lista Enlazada Simple de Productos

### ¿Qué se implementó?

En este primer avance se desarrolló la estructura central del sistema: una **lista enlazada simple** llamada `ListaProductos`, que permite gestionar el inventario de productos de la tienda.

### Clases implementadas

#### `Producto` 
Representa un producto dentro del sistema. Contiene los siguientes atributos:

| Atributo           | Tipo              | Descripción                                                  |
|--------------------|-------------------|--------------------------------------------------------------|
| `nombre`           | `String`          | Nombre del producto                                          |
| `precio`           | `double`          | Precio unitario del producto                                 |
| `categoria`        | `String`          | Categoría a la que pertenece el producto                     |
| `fechaVencimiento` | `String`          | Fecha de vencimiento (opcional, aplica solo a algunos productos) |
| `cantidad`         | `int`             | Cantidad de unidades disponibles en inventario               |
| `listaImagenes`    | `ArrayList<String>` | Lista de rutas a imágenes del producto (dentro del proyecto) |
| `siguiente    `    | `Producto`        | Atributo dedicado para el funcionamiento de la lista enlazada|


#### `ListaProductos` 
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


#### `Menu`
Contiene el menu de opciones para que el usuario pueda interactuar con el programa


#### `Main` 
Contiene el método `main()` que invoca al menú dando la entrada al programa

---

## 🗂️ Estructura del Proyecto

```
Grup6-ProyectoEstructuraDeDatos/
├── src/
│   ├── Main.java
│   ├── Producto.java
│   ├── Menu.java
│   └── ListaProductos.java
├── imagenes/
│   └── (imágenes de los productos)
└── README.md
└── .gitignore
```

Las rutas de las imágenes almacenadas en `listaImagenes` hacen referencia al directorio `imagenes/` dentro del proyecto.

---

## ▶️ ¿Cómo ejecutar el proyecto?

1. Clonar o descargar el repositorio.
2. Abrir el proyecto en un IDE.
3. Compilar y ejecutar la clase `Main.java`.
4. Interactuar con el sistema a través del menú de consola.

---

## 👥 Integrantes del Grupo

| Nombre completo         |      correo electrónico    |
|-------------------------|----------------------------|
| Fabián Vargas Hidalgo   | fvargash@ucenfotec.ac.cr   |
| Samuel Madrigal Ugalde  | smadrigalu@ucenfotec.ac.cr |

---

## 📚 Curso

**Estructuras de Datos**  
Fecha de entrega Primer Avance: 22 de febrero 
