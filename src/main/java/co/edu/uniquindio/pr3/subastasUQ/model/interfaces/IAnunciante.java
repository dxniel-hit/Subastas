package co.edu.uniquindio.pr3.subastasUQ.model.interfaces;

import co.edu.uniquindio.pr3.subastasUQ.exceptions.AnuncioException;
import co.edu.uniquindio.pr3.subastasUQ.exceptions.ProductoException;
import co.edu.uniquindio.pr3.subastasUQ.model.Anuncio;
import co.edu.uniquindio.pr3.subastasUQ.model.Compra;
import co.edu.uniquindio.pr3.subastasUQ.model.Producto;
import co.edu.uniquindio.pr3.subastasUQ.model.enumerations.TipoProducto;
import javafx.scene.image.Image;

public interface IAnunciante {

    public boolean crearAnuncio(String codigo, String fechaInicio, String fechaFinal, String nombreAnunciante, String codigoProducto) throws AnuncioException, ProductoException;

    public void imprimirAnuncio(String codigo) throws AnuncioException;

    public boolean actualizarAnuncio(String codigo, String fechaInicio, String fechaFinal, String nombreAnunciante, String codigoProducto) throws AnuncioException, ProductoException;

    public boolean eliminarAnuncio(String codigo) throws AnuncioException;


}
