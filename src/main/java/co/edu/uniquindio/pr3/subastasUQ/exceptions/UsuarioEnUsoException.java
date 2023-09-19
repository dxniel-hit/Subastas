package co.edu.uniquindio.pr3.subastasUQ.exceptions;

public class UsuarioEnUsoException extends Exception{

    public UsuarioEnUsoException() {
        super("El Usuario ya se encuentra en uso");
    }

}
