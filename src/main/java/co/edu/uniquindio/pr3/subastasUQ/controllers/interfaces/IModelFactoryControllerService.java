package co.edu.uniquindio.pr3.subastasUQ.controllers.interfaces;

import co.edu.uniquindio.pr3.subastasUQ.exceptions.AnuncianteException;
import co.edu.uniquindio.pr3.subastasUQ.exceptions.CompradorException;
import co.edu.uniquindio.pr3.subastasUQ.exceptions.UsuarioEnUsoException;
import co.edu.uniquindio.pr3.subastasUQ.mapping.dto.*;
import co.edu.uniquindio.pr3.subastasUQ.model.*;
import co.edu.uniquindio.pr3.subastasUQ.model.enumerations.TipoUsuario;
import co.edu.uniquindio.pr3.subastasUQ.viewControllers.*;

import java.util.*;

public interface IModelFactoryControllerService {

    public void initVentanaPrincipalViewController(VentanaPrincipalViewController ventanaPrincipalViewController);
    public void initRegistroViewController(RegistroViewController registroViewController);
    public void initLoginViewController(LoginViewController loginViewController) ;
    public void initMiCuentaViewController(MiCuentaViewController miCuentaViewController);
    public void initProductosViewControlles(ProductosViewController productosViewController);
    public void initMisAnunciosViewController(MisAnunciosViewController misAnunciosViewController);
    public void initSubastasViewController(SubastasViewController subastasViewController);
    public void initMisPujasViewController(MisPujasViewController misPujasViewController);
    public boolean crearAnunciante(String nombres, String apellidos, String identificacion, int edad, String usuario, String contrasenia, String email) throws UsuarioEnUsoException, AnuncianteException;
    public boolean crearComprador(String nombres, String apellidos, String identificacion, int edad, String usuario, String contrasenia, String email) throws UsuarioEnUsoException, CompradorException;
    public int encontrarPosUsuario(String usuario);
    public Usuario obtenerUsuario(int pos);
    public Anunciante obtenerAnunciante(String identificacion);
    public Comprador obtenerComprador(String identificacion);
    public void setMiAnunciante(Anunciante miAnunciante);
    public void setMiComprador(Comprador miComprador);
    public void resetCuenta(String usuario);
    public void resetCuenta();
    public void habilitarPestaniasAnunciante();
    public void habilitarPestaniasComprador();
    public boolean actualizarAnuciante(String nombres, String apellidos, String identificacion, int edad, String email) throws AnuncianteException;
    public boolean actualizarComprador(String nombres, String apellidos, String identificacion, int edad, String email) throws CompradorException;
    public String cambiarContrasenia(String identifiacion, TipoUsuario tipoUsuario, String nuevaContrasenia) throws CompradorException, AnuncianteException;
    public String cambiarUsuario(String identifiacion, TipoUsuario tipoUsuario, String nuevoUsuario) throws UsuarioEnUsoException, CompradorException, AnuncianteException;
    public boolean eliminarAnunciante(String identificacion) throws AnuncianteException;
    public boolean eliminarComprador(String identificacion) throws CompradorException;
    public List<ProductoDTO> obtenerProductosAnunciante();
    public boolean agregarProducto(ProductoDTO productoDTO);
    public boolean renovarProducto(String codigoProducto, ProductoDTO productoDTO);
    public boolean expelerProducto(String codigo);
    public ProductoDTO obtenerProductoDto(String codigoProducto);
    public Anuncio obtenerAnuncio(String codigoAnuncio);
    public boolean actualizarAnuncio(String codigoAnuncio, AnuncioDTO productoDTO);
    public boolean eliminarAnuncio(String codigo);
    public List<PujaDTO> obtenerPujasDto(List<Puja> listaPujas);
    public boolean agregarAnuncio(AnuncioDTO anuncioDTO);
    public void refrescarTablaSubastas();
    public void initAnuncioSelcionado(AnuncioDTO anuncioSeleccionado);
    public void resetSeleccionAnuncio();
    public boolean agregarPuja(PujaDTO pujaDto);
    public void convertAnunciosTxtToCsv(String outputFolderPath);
    public void convertComprasTxtToCsv(String outputFolderPath);

}
