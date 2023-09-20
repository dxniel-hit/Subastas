package co.edu.uniquindio.pr3.subastasUQ.model;

public class Puja {

    //Atributos de la clase
    private Anuncio anuncio;
    private Comprador comprador;
    private Double valor;

    //Constructores de la clase Puja
    public Puja(){

    }

    public Puja(Anuncio anuncio, Comprador comprador, Double valor) {
        this.anuncio = anuncio;
        this.comprador = comprador;
        this.valor = valor;
    }

    //getters() & setters()

    public Anuncio getAnuncio() {
        return anuncio;
    }
    public void setAnuncio(Anuncio anuncio) {
        this.anuncio = anuncio;
    }
    public Comprador getComprador() {
        return comprador;
    }
    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }
    public Double getValor() {
        return valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }

    //toString()

    @Override
    public String toString() {
        return "Puja{" +
                "anuncio=" + anuncio.getCodigo() +
                ", comprador=" + comprador.getIdentificacion() +
                ", valor=" + valor +
                '}';
    }
}
