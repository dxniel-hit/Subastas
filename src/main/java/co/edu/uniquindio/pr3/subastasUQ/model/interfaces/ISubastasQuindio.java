package com.uq.pr3.model.interfaces;


import com.uq.pr3.model.Anunciante;
import com.uq.pr3.model.Comprador;

public interface ISubastasQuindio {

    Anunciante crearAnunciante();

    void imprimirAnunciante();

    boolean eliminarAnunciante();

    boolean actualizarAnunciante();

    Comprador crearComprador();

    void imprimirComprador();

    boolean eliminarComprador();

    boolean actualizarComprador();


}
