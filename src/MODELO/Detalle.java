package MODELO;

public class Detalle {

    private int id;

    private String codigopro;
    private String marca;
    private int tall;
    private int cantidad;
    private int precio;
    private int valor;
    private int total;
    private int id_venta;
    private String fecha;

    public Detalle() {
    }

    public Detalle(int id, String codigopro, String marca, int tall, int cantidad, int precio, int valor, int total, int id_venta,String fecha) {
        this.id = id;
        this.codigopro = codigopro;
        this.marca = marca;
        this.tall = tall;
        this.cantidad = cantidad;
        this.precio = precio;
        this.valor = valor;
        this.total = total;
        this.id_venta = id_venta;
        this.fecha=fecha;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigopro() {
        return codigopro;
    }

    public void setCodigopro(String codigopro) {
        this.codigopro = codigopro;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getTall() {
        return tall;
    }

    public void setTall(int tall) {
        this.tall = tall;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }
    
    

}
