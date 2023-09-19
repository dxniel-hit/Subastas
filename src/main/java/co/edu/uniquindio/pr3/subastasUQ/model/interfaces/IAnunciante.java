package co.edu.uniquindio.pr3.subastasUQ.model.interfaces;

import co.edu.uniquindio.pr3.subastasUQ.model.Anuncio;
import co.edu.uniquindio.pr3.subastasUQ.model.Producto;

public interface IAnunciante {

    public Anuncio crearAnuncio();

    public void imprimirAnuncio();

    public boolean actualizarAnuncio();

    public Producto crearProducto();

    public void imprimirProducto();

    public boolean actualizarProducto();

    public boolean eliminarProducto();
}
