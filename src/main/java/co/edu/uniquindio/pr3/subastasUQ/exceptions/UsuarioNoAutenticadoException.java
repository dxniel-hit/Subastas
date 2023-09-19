package co.edu.uniquindio.pr3.subastasUQ.exceptions;

public class UsuarioNoAutenticadoException extends Exception{

    public UsuarioNoAutenticadoException() {
        super("No se encuentra un usuario autenticado: Porfavor iniciar sesion");
    }

}
