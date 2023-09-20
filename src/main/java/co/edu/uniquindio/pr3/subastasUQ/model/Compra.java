package co.edu.uniquindio.pr3.subastasUQ.model;

public class Compra {

    //Atributos de la clase
    private Producto producto;
    private Double valor;
    private String fecha;
    private Boolean isPago;
    private Comprador comprador;
    private Anunciante anunciante;

    //Metodos constructor de la clase
    public Compra(){

    }
    public Compra(Producto producto, Double valor, String fecha, Comprador comprador, Anunciante anunciante) {
        this.producto = producto;
        this.valor = valor;
        this.fecha = fecha;
        this.isPago = false;
        this.comprador = comprador;
        this.anunciante = anunciante;
    }

    //getters() & setters()
    public Producto getProducto() {
        return producto;
    }
    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    public Double getValor() {
        return valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public Boolean getPago() {
        return isPago;
    }
    public void setPago(Boolean pago) {
        isPago = pago;
    }
    public Comprador getComprador() {
        return comprador;
    }
    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }
    public Anunciante getAnunciante() {
        return anunciante;
    }
    public void setAnunciante(Anunciante anunciante) {
        this.anunciante = anunciante;
    }

    //toString()
    @Override
    public String toString() {
        return "Compra{" +
                "producto=" + producto.getCodigo() +
                ", valor=" + valor +
                ", fecha='" + fecha + '\'' +
                ", isPago=" + isPago +
                ", comprador=" + comprador.getIdentificacion() +
                ", anunciante=" + anunciante.getIdentificacion() +
                '}';
    }
}
