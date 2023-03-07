
package MODELO;

public class Gasto {
    
    private int id;
    private String tipogasto;
    private String descripcion;
    private int cantidad;
    private String fecha;

    public Gasto() {
    }

    public Gasto(int id, String tipogasto, String descripcion, int cantidad, String fecha) {
        this.id = id;
        this.tipogasto = tipogasto;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipogasto() {
        return tipogasto;
    }

    public void setTipogasto(String tipogasto) {
        this.tipogasto = tipogasto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
    
}
