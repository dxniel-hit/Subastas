package co.edu.uniquindio.pr3.subastasUQ.model;

import co.edu.uniquindio.pr3.subastasUQ.exceptions.PujaException;
import co.edu.uniquindio.pr3.subastasUQ.model.enumerations.TipoUsuario;
import co.edu.uniquindio.pr3.subastasUQ.model.interfaces.IComprador;

import java.util.ArrayList;
import java.util.List;

public class Comprador extends Usuario implements IComprador {

    //Atributos de la clase
    private List<Puja> listaPujas;
    private List<Integer> cantidadDeVecesPujada;
    private List<Compra> listaCompras;

    //Constructores
    public Comprador(){

    }

    public Comprador(String nombres, String apellidos, String identificacion, Integer edad, Subasta subasta, String usuario, String contrasenia, String email, Boolean isAutenticado, TipoUsuario tipoUsuario) {
        super(nombres, apellidos, identificacion, edad, subasta, usuario, contrasenia, email, isAutenticado, tipoUsuario);
        this.listaPujas = new ArrayList<Puja>();
        this.cantidadDeVecesPujada = new ArrayList<Integer>();
        this.listaCompras = new ArrayList<Compra>();
    }

    //getters() & setters()
    public List<Puja> getListaPujas() {
        return listaPujas;
    }
    public void setListaPujas(List<Puja> listaPujas) {
        this.listaPujas = listaPujas;
    }
    public List<Integer> getCantidadDeVecesPujada() {
        return cantidadDeVecesPujada;
    }
    public void setCantidadDeVecesPujada(List<Integer> cantidadDeVecesPujada) {
        this.cantidadDeVecesPujada = cantidadDeVecesPujada;
    }
    public List<Compra> getListaCompras() {
        return listaCompras;
    }
    public void setListaCompras(List<Compra> listaCompras) {
        this.listaCompras = listaCompras;
    }

    //toString()
    @Override
    public String toString() {
        return "Comprador{" +
                "listaPujas=" + listaPujas +
                ", cantidadDeVecesPujada=" + cantidadDeVecesPujada +
                ", listaCompras=" + listaCompras +
                '}';
    }

    //Metodos propios de la clase Comprador
    public int devolverNumeroPujasEnAnuncio(String codigoAnuncio){
        return (int) listaPujas.stream().filter(p -> p.getAnuncio().getCodigo().equals(codigoAnuncio)).count();
    }

    @Override
    public boolean realizarPuja(String codigoAnuncio, Double valor, String fecha) throws PujaException {
        Anuncio a = getSubastasQuindio().obtenerAnuncio(codigoAnuncio);
        if(a==null) throw new PujaException("El Anuncio No se encuentra creado");
        if(!verificarCantidadPujas(codigoAnuncio)) throw new PujaException("Ya se han realizado 3 pujas por el anuncio");
        if(valor<a.getProducto().getValorInicial()) throw new PujaException("El valor de la puja es menor al valor inicial del producto");
        if(!Subasta.isEntreFechas(fecha, a.getFechaInicio(), a.getFechaFinal())) throw new PujaException("La puja no se puede realizar en la fecha actual");
        Puja p = new Puja(a, this, valor, fecha);
        a.getListaPujas().add(p);  //Se añade la puja a la listaPujas del Anuncio
        getListaPujas().add(p);    //Se añade la puja a la listaPujas del Comprador
        return true;
    }

    @Override
    public boolean verificarCantidadPujas(String codigoAnuncio) throws PujaException {
        return devolverNumeroPujasEnAnuncio(codigoAnuncio) < 3;
    }

    @Override
    public boolean eliminarPuja(String codigoAnuncio, Double valor, String fecha) {
        try{
            listaPujas.remove(new Puja(getSubastasQuindio().obtenerAnuncio(codigoAnuncio), this, valor, fecha));

        }catch(Exception e){
            return false;
        }
        return true;
    }
}
