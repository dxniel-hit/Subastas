package co.edu.uniquindio.pr3.subastasUQ.model.interfaces;


import co.edu.uniquindio.pr3.subastasUQ.model.Anunciante;
import co.edu.uniquindio.pr3.subastasUQ.model.Comprador;

public interface ISubastasQuindio {

    public Anunciante crearAnunciante();

    public void imprimirAnunciante();

    public boolean eliminarAnunciante();

    public boolean actualizarAnunciante();

    public Comprador crearComprador();

    public void imprimirComprador();

    public boolean eliminarComprador();

    public boolean actualizarComprador();


}
