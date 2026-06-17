// Pedido listo para ser despachado a una sucursal
public class Pedido {
    private int id;
    private String sucursalDestino;
    private String descripcion;

    public Pedido(int id, String sucursalDestino, String descripcion) {
        this.id = id;
        this.sucursalDestino = sucursalDestino;
        this.descripcion = descripcion;
    }

    public int getId() { return id; }
    public String getSucursalDestino() { return sucursalDestino; }
    public String getDescripcion() { return descripcion; }

    public String toString() {
        return "Pedido#" + id + " -> " + sucursalDestino + " (" + descripcion + ")";
    }
}
