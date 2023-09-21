package co.edu.uniquindio.pr3.subastasUQ.test;

import co.edu.uniquindio.pr3.subastasUQ.exceptions.CompradorException;
import co.edu.uniquindio.pr3.subastasUQ.exceptions.UsuarioEnUsoException;
import co.edu.uniquindio.pr3.subastasUQ.model.Comprador;
import co.edu.uniquindio.pr3.subastasUQ.model.SubastasQuindio;
import co.edu.uniquindio.pr3.subastasUQ.model.Usuario;
import co.edu.uniquindio.pr3.subastasUQ.model.interfaces.ISubastasQuindio;

import java.util.List;

public class TestClass {

    public static void main(String[] args) {

        SubastasQuindio subastasQuindio = null;
        Boolean isCreado = null;

        try {
            subastasQuindio = new SubastasQuindio("UQ", "Calle 1");
            isCreado = subastasQuindio.crearComprador("Julio", "Cesar", "1092455966", 12, subastasQuindio, "Jucesar", "12345", "julio@hotmail.com", true);
        } catch (CompradorException compradorException) {
            compradorException.printStackTrace();
        } catch (UsuarioEnUsoException usuarioEnUsoException) {
            usuarioEnUsoException.printStackTrace();
        }
        System.out.println(isCreado);

        List<Usuario> listaUsuarios = subastasQuindio.getListaUsuarios();

        for (Usuario usuario: listaUsuarios) {
            System.out.println(usuario.getUsuario());
        }
    }
}
