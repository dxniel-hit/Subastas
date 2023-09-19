package com.uq.pr3.model.interfaces;

import com.uq.pr3.model.Anuncio;
import com.uq.pr3.model.Producto;

public interface IAnunciante {

    Anuncio crearAnuncio();

    void imprimirAnuncio();

    boolean actualizarAnuncio();

    Producto crearProducto();

    void imprimirProducto();

    boolean actualizarProducto();

    boolean eliminarProducto();
}
