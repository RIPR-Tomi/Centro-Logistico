// Cola FIFO (First In First Out) para la linea de expedicion de pedidos.
// Implementada con lista enlazada simple: encolar al final, desencolar del frente.
public class Cola {

    // Nodo interno de la cola
    private class Nodo {
        Pedido pedido;
        Nodo siguiente;

        Nodo(Pedido pedido) {
            this.pedido = pedido;
            this.siguiente = null;
        }
    }

    private Nodo frente;
    private Nodo fin;
    private int tamanio;

    public Cola() {
        this.frente = null;
        this.fin = null;
        this.tamanio = 0;
    }

    // Agregar pedido al final de la cola
    public void encolar(Pedido pedido) {
        Nodo nuevo = new Nodo(pedido);
        if (frente == null) {
            // Cola vacia: el nuevo es frente y fin a la vez
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.siguiente = nuevo;
            fin = nuevo;
        }
        tamanio++;
    }

    // Sacar y devolver el pedido del frente. Devuelve null si esta vacia.
    public Pedido desencolar() {
        if (frente == null) {
            return null;
        }
        Pedido p = frente.pedido;
        frente = frente.siguiente;
        if (frente == null) {
            fin = null;  // la cola quedo vacia
        }
        tamanio--;
        return p;
    }

    // Ver el pedido del frente sin sacarlo
    public Pedido verFrente() {
        if (frente == null) {
            return null;
        }
        return frente.pedido;
    }

    // Obtener todos los pedidos en orden de cola (frente a fin) sin sacarlos
    public Pedido[] obtenerTodos() {
        Pedido[] arr = new Pedido[tamanio];
        Nodo actual = frente;
        int i = 0;
        while (actual != null) {
            arr[i] = actual.pedido;
            actual = actual.siguiente;
            i++;
        }
        return arr;
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public int getTamanio() {
        return tamanio;
    }
}
