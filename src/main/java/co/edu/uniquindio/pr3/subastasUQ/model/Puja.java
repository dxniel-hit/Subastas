package co.edu.uniquindio.pr3.subastasUQ.model;

import java.util.Objects;

public class Puja {

    //Atributos de la clase
    private Anuncio anuncio;
    private Comprador comprador;
    private Double valor;
    private String fecha;

    //Constructores de la clase Puja
    public Puja(){

    }

    public Puja(Anuncio anuncio, Comprador comprador, Double valor, String fecha) {
        this.anuncio = anuncio;
        this.comprador = comprador;
        this.valor = valor;
        this.fecha = fecha;
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
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    //toString()
    @Override
    public String toString() {
        return "Puja{" +
                "anuncio=" + anuncio.getCodigo() +
                ", comprador=" + comprador.getIdentificacion() +
                ", valor=" + valor +
                ", fecha=" + fecha +
                '}';
    }

    //hasCode() & equals()

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Puja puja)) return false;
        return Objects.equals(getAnuncio(), puja.getAnuncio()) && Objects.equals(getComprador(), puja.getComprador()) && Objects.equals(getValor(), puja.getValor()) && Objects.equals(getFecha(), puja.getFecha());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAnuncio(), getComprador(), getValor(), getFecha());
    }

}
