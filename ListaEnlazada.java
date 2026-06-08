public class ListaEnlazada {

    // Nodo interno de la lista
    private class Nodo {
        Producto producto;
        Nodo siguiente;

        Nodo(Producto producto) {
            this.producto = producto;
            this.siguiente = null;
        }
    }

    private Nodo cabeza;
    private int tamanio;

    public ListaEnlazada() {
        this.cabeza = null;
        this.tamanio = 0;
    }

    // Insertar producto manteniendo la lista ordenada por stock (menor primero)
    public void insertar(Producto producto) {
        Nodo nuevo = new Nodo(producto);

        // Caso 1: lista vacia o el nuevo va al principio
        if (cabeza == null || producto.getStock() < cabeza.producto.getStock()) {
            nuevo.siguiente = cabeza;
            cabeza = nuevo;
            tamanio++;
            return;
        }

        // Caso 2: buscar la posicion correcta recorriendo la lista
        Nodo actual = cabeza;
        while (actual.siguiente != null
                && actual.siguiente.producto.getStock() <= producto.getStock()) {
            actual = actual.siguiente;
        }
        nuevo.siguiente = actual.siguiente;
        actual.siguiente = nuevo;
        tamanio++;
    }

    // Ver el producto con menor stock (es la cabeza, no se saca)
    public Producto verMinimo() {
        if (cabeza == null) {
            return null;
        }
        return cabeza.producto;
    }

    // Sacar el producto con menor stock
    public Producto extraerMinimo() {
        if (cabeza == null) {
            return null;
        }
        Producto minimo = cabeza.producto;
        cabeza = cabeza.siguiente;
        tamanio--;
        return minimo;
    }

    // Actualizar el stock de un producto: lo saco, le cambio el stock y lo reinserto
    // para que quede en la posicion correcta segun el nuevo valor
    public void actualizarStock(String codigo, double nuevoStock) {
        Producto producto = eliminarYDevolver(codigo);
        if (producto != null) {
            producto.setStock(nuevoStock);
            insertar(producto);
        }
    }

    // Eliminar producto por codigo
    public boolean eliminar(String codigo) {
        return eliminarYDevolver(codigo) != null;
    }

    // Auxiliar: elimina y devuelve el producto, o null si no existe
    private Producto eliminarYDevolver(String codigo) {
        if (cabeza == null) {
            return null;
        }
        // Caso especial: es la cabeza
        if (cabeza.producto.getCodigo().equals(codigo)) {
            Producto p = cabeza.producto;
            cabeza = cabeza.siguiente;
            tamanio--;
            return p;
        }
        // Buscar en el resto
        Nodo actual = cabeza;
        while (actual.siguiente != null) {
            if (actual.siguiente.producto.getCodigo().equals(codigo)) {
                Producto p = actual.siguiente.producto;
                actual.siguiente = actual.siguiente.siguiente;
                tamanio--;
                return p;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    // Obtener los N productos con menor stock (los primeros N de la lista)
    public Producto[] obtenerProductosCriticos(int cantidad) {
        int n = Math.min(cantidad, tamanio);
        Producto[] criticos = new Producto[n];
        Nodo actual = cabeza;
        for (int i = 0; i < n; i++) {
            criticos[i] = actual.producto;
            actual = actual.siguiente;
        }
        return criticos;
    }

    public int getTamanio() {
        return tamanio;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }
}