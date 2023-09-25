package co.edu.uniquindio.pr3.subastasUQ.test;

import co.edu.uniquindio.pr3.subastasUQ.exceptions.CompradorException;
import co.edu.uniquindio.pr3.subastasUQ.exceptions.UsuarioEnUsoException;
import co.edu.uniquindio.pr3.subastasUQ.model.*;
import co.edu.uniquindio.pr3.subastasUQ.model.Subasta;

import java.util.List;

public class TestClass {

    public static void main(String[] args) {

        Subasta subasta = null;
        Boolean isCreado = null;

        try {
            subasta = new Subasta("UQ", "Calle 1");
            isCreado = subasta.crearComprador("Julio", "Cesar", "1092455966", 12, subasta, "Jucesar", "12345", "julio@hotmail.com", true);
        } catch (CompradorException compradorException) {
            compradorException.printStackTrace();
        } catch (UsuarioEnUsoException usuarioEnUsoException) {
            usuarioEnUsoException.printStackTrace();
        }
        System.out.println(isCreado);

        List<Usuario> listaUsuarios = subasta.getListaUsuarios();

        for (Usuario usuario: listaUsuarios) {
            System.out.println(usuario.getUsuario());
        }
    }
}
