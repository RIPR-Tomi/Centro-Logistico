// Grafo no dirigido ponderado para modelar la red de sucursales.
// Implementado con matriz de adyacencia: matriz[i][j] guarda la distancia
// en km entre las sucursales i y j (0 significa que no hay ruta directa).
//
// Para encontrar la ruta de menor distancia entre dos sucursales se usa
// DFS con backtracking: se exploran recursivamente todos los caminos posibles
// sin repetir sucursales y se conserva el de menor distancia total.
public class Grafo {

    private static final int MAX_SUCURSALES = 50;

    private String[] sucursales;
    private double[][] matriz;
    private int cantidad;

    // Atributos auxiliares usados durante la busqueda de camino mas corto
    private int[] caminoActual;
    private int[] mejorCamino;
    private int longitudMejorCamino;
    private double distanciaMejorCamino;
    private boolean[] visitado;

    public Grafo() {
        sucursales = new String[MAX_SUCURSALES];
        matriz = new double[MAX_SUCURSALES][MAX_SUCURSALES];
        cantidad = 0;
    }

    // Agregar una sucursal al grafo
    public boolean agregarSucursal(String nombre) {
        if (existeSucursal(nombre)) {
            return false;
        }
        if (cantidad == MAX_SUCURSALES) {
            return false;
        }
        sucursales[cantidad] = nombre;
        cantidad = cantidad + 1;
        return true;
    }

    // Agregar una ruta bidireccional entre dos sucursales con su distancia en km
    public boolean agregarRuta(String suc1, String suc2, double distanciaKm) {
        int i = obtenerIndice(suc1);
        int j = obtenerIndice(suc2);
        if (i == -1 || j == -1 || distanciaKm <= 0) {
            return false;
        }
        matriz[i][j] = distanciaKm;
        matriz[j][i] = distanciaKm;  // grafo no dirigido
        return true;
    }

    // Buscar el indice interno de una sucursal por nombre
    private int obtenerIndice(String nombre) {
        int posicion = -1;
        int i = 0;
        while (i < cantidad && posicion == -1) {
            if (sucursales[i].equals(nombre)) {
                posicion = i;
            }
            i = i + 1;
        }
        return posicion;
    }

    public boolean existeSucursal(String nombre) {
        return obtenerIndice(nombre) != -1;
    }

    public String[] listarSucursales() {
        String[] copia = new String[cantidad];
        for (int i = 0; i < cantidad; i++) {
            copia[i] = sucursales[i];
        }
        return copia;
    }

    public int getCantidad() {
        return cantidad;
    }

    // Calcular la ruta de menor distancia entre dos sucursales.
    // Devuelve el camino como arreglo de nombres, o null si no hay ruta.
    // Despues de llamar a este metodo, getUltimaDistancia() retorna la distancia total.
    public String[] caminoMasCorto(String origen, String destino) {
        int posOrigen = obtenerIndice(origen);
        int posDestino = obtenerIndice(destino);
        if (posOrigen == -1 || posDestino == -1) {
            return null;
        }

        // Reiniciar variables auxiliares
        caminoActual = new int[cantidad];
        mejorCamino = new int[cantidad];
        visitado = new boolean[cantidad];
        longitudMejorCamino = 0;
        distanciaMejorCamino = -1;  // -1 indica que todavia no se encontro ningun camino

        // Empiezo desde el origen
        caminoActual[0] = posOrigen;
        visitado[posOrigen] = true;

        // Exploracion DFS recursiva
        buscarCaminoDFS(posOrigen, posDestino, 1, 0.0);

        if (distanciaMejorCamino == -1) {
            return null;  // no se encontro ningun camino
        }

        // Construir el resultado con los nombres de las sucursales
        String[] resultado = new String[longitudMejorCamino];
        for (int i = 0; i < longitudMejorCamino; i++) {
            resultado[i] = sucursales[mejorCamino[i]];
        }
        return resultado;
    }

    // DFS con backtracking: prueba todos los caminos posibles desde 'actual'
    // hasta llegar al destino, y va guardando el de menor distancia.
    private void buscarCaminoDFS(int actual, int destino, int largoCamino, double distanciaAcumulada) {

        if (actual == destino) {
            // Llegue al destino: comparo si este camino es mejor que el guardado
            if (distanciaMejorCamino == -1 || distanciaAcumulada < distanciaMejorCamino) {
                distanciaMejorCamino = distanciaAcumulada;
                longitudMejorCamino = largoCamino;
                for (int i = 0; i < largoCamino; i++) {
                    mejorCamino[i] = caminoActual[i];
                }
            }
            return;
        }

        // Probar todos los vecinos no visitados
        for (int v = 0; v < cantidad; v++) {
            if (matriz[actual][v] > 0 && !visitado[v]) {
                // Marco el vecino como visitado y lo agrego al camino actual
                visitado[v] = true;
                caminoActual[largoCamino] = v;

                // Llamada recursiva
                buscarCaminoDFS(v, destino, largoCamino + 1, distanciaAcumulada + matriz[actual][v]);

                // Backtracking: desmarco el vecino para probar otros caminos
                visitado[v] = false;
            }
        }
    }

    // Devuelve la distancia del ultimo camino calculado con caminoMasCorto.
    // Retorna -1 si no hubo camino o no se ejecuto la busqueda.
    public double getUltimaDistancia() {
        return distanciaMejorCamino;
    }
}
