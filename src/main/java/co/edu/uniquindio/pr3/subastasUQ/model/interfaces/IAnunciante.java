package co.edu.uniquindio.pr3.subastasUQ.model.interfaces;

import co.edu.uniquindio.pr3.subastasUQ.exceptions.AnuncianteException;
import co.edu.uniquindio.pr3.subastasUQ.exceptions.AnuncioException;
import co.edu.uniquindio.pr3.subastasUQ.exceptions.ProductoException;
import co.edu.uniquindio.pr3.subastasUQ.model.*;
import co.edu.uniquindio.pr3.subastasUQ.model.enumerations.TipoProducto;
import javafx.scene.image.Image;

public interface IAnunciante {

    public boolean crearAnuncio(String codigo, String fechaInicio, String fechaFinal, String nombreAnunciante, String codigoProducto) throws AnuncioException, ProductoException;

    public void imprimirAnuncio(String codigo) throws AnuncioException;

    public boolean actualizarAnuncio(String codigo, String fechaInicio, String fechaFinal, String nombreAnunciante, String codigoProducto) throws AnuncioException, ProductoException;

    public boolean eliminarAnuncio(String codigo) throws AnuncioException;

    public boolean crearProducto(String codigo, String nombre, String descripcion, String direccionImagen, Double valorInicial, TipoProducto tipoProducto) throws ProductoException;

    public void imprimirProducto(String codigo) throws ProductoException;

    public boolean actualizarProducto(String codigo, Producto producto) throws ProductoException;

    public boolean eliminarProducto(String codigo) throws ProductoException;

    public Compra realizarVenta(String codigoAnuncio, String codigoProducto, Double valor, String fecha, String identificacionComprador, Anunciante anunciante) throws AnuncianteException;

}
