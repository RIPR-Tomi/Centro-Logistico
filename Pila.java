// Pila LIFO (Last In First Out) para registrar movimientos de inventario
// y poder deshacer el ultimo. Implementada con lista enlazada simple.
public class Pila {

    // Nodo interno de la pila
    private class Nodo {
        Movimiento movimiento;
        Nodo siguiente;

        Nodo(Movimiento movimiento) {
            this.movimiento = movimiento;
            this.siguiente = null;
        }
    }

    private Nodo tope;
    private int tamanio;

    public Pila() {
        this.tope = null;
        this.tamanio = 0;
    }

    // Apilar un movimiento en el tope
    public void apilar(Movimiento movimiento) {
        Nodo nuevo = new Nodo(movimiento);
        nuevo.siguiente = tope;
        tope = nuevo;
        tamanio++;
    }

    // Sacar y devolver el movimiento del tope. Devuelve null si esta vacia.
    public Movimiento desapilar() {
        if (tope == null) {
            return null;
        }
        Movimiento m = tope.movimiento;
        tope = tope.siguiente;
        tamanio--;
        return m;
    }

    // Ver el movimiento del tope sin sacarlo
    public Movimiento verTope() {
        if (tope == null) {
            return null;
        }
        return tope.movimiento;
    }

    public boolean estaVacia() {
        return tope == null;
    }

    public int getTamanio() {
        return tamanio;
    }
}
