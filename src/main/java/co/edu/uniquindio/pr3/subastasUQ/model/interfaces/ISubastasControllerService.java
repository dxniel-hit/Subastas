package co.edu.uniquindio.pr3.subastasUQ.model.interfaces;


import co.edu.uniquindio.pr3.subastasUQ.exceptions.AnuncianteException;
import co.edu.uniquindio.pr3.subastasUQ.exceptions.CompradorException;
import co.edu.uniquindio.pr3.subastasUQ.exceptions.UsuarioEnUsoException;
import co.edu.uniquindio.pr3.subastasUQ.model.Subasta;

public interface ISubastasControllerService {

    public boolean crearAnunciante(String nombres, String apellidos, String identificacion, int edad, Subasta subasta, String usuario, String contrasenia, String email, Boolean isAutenticado) throws AnuncianteException, UsuarioEnUsoException;

    public void imprimirAnunciante(String identificacion) throws AnuncianteException;

    public boolean eliminarAnunciante(String identificacion) throws AnuncianteException;

    public boolean actualizarAnunciante(String nombre, String direccion, String identificacion, int edad, String email) throws AnuncianteException;

    public boolean crearComprador(String nombres, String apellidos, String identificacion, int edad, Subasta subasta, String usuario, String contrasenia, String email, Boolean isAutenticado) throws CompradorException, UsuarioEnUsoException;

    public void imprimirComprador(String identificacion) throws CompradorException;

    public boolean eliminarComprador(String identificacion) throws CompradorException;

    public boolean actualizarComprador(String nombres, String apellidos, String identificacion, int edad, String email) throws CompradorException;


}
