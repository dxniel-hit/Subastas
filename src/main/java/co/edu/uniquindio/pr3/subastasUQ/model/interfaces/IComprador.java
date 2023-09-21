package co.edu.uniquindio.pr3.subastasUQ.model.interfaces;

import co.edu.uniquindio.pr3.subastasUQ.exceptions.PujaException;

public interface IComprador {

    public boolean realizarPuja(String codigoAnuncio, Double valor, String fecha) throws PujaException;

    public boolean verificarCantidadPujas(String codigoAnuncio) throws PujaException;

    public boolean eliminarPuja(String codigoAnuncio, Double valor, String fecha);
}
