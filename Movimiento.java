// Registro de una operacion de inventario para poder deshacerla
public class Movimiento {

    // Tipos de operacion posibles
    public static final int ALTA = 1;
    public static final int BAJA = 2;
    public static final int MODIFICACION_STOCK = 3;

    private int tipo;
    private Producto producto;
    private double stockAnterior;  // solo se usa en MODIFICACION_STOCK

    public Movimiento(int tipo, Producto producto, double stockAnterior) {
        this.tipo = tipo;
        this.producto = producto;
        this.stockAnterior = stockAnterior;
    }

    public int getTipo() { return tipo; }
    public Producto getProducto() { return producto; }
    public double getStockAnterior() { return stockAnterior; }

    public String getTipoTexto() {
        if (tipo == ALTA) return "ALTA";
        if (tipo == BAJA) return "BAJA";
        if (tipo == MODIFICACION_STOCK) return "MODIFICACION DE STOCK";
        return "DESCONOCIDO";
    }

    public String toString() {
        if (tipo == MODIFICACION_STOCK) {
            return getTipoTexto() + " - " + producto.getCodigo()
                    + " (stock anterior: " + stockAnterior + ")";
        }
        return getTipoTexto() + " - " + producto.getCodigo();
    }
}
