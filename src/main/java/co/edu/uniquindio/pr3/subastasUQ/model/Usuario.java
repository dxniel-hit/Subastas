package co.edu.uniquindio.pr3.subastasUQ.model;

import co.edu.uniquindio.pr3.subastasUQ.model.enumerations.TipoUsuario;

public class Usuario extends Persona{

    //Atributos de la clase
    private String usuario;
    private String contrasenia;
    private Boolean isAutenticado;
    private TipoUsuario tipoUsuario;



    //Constructores de la clase

    public Usuario() {

    }

    public Usuario(String nombres, String apellidos, String identificacion, Integer edad, String usuario, String contrasenia, Boolean isAutenticado, TipoUsuario tipoUsuario) {
        super(nombres, apellidos, identificacion, edad);
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.isAutenticado = isAutenticado;
        this.tipoUsuario = tipoUsuario;
    }

    //Getters y setters

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Boolean getAutenticado() {
        return isAutenticado;
    }

    public void setAutenticado(Boolean autenticado) {
        isAutenticado = autenticado;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    //toString()

    @Override
    public String toString() {
        return "Usuario{" +
                "usuario='" + usuario + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                ", isAutenticado=" + isAutenticado +
                ", tipoUsuario=" + tipoUsuario +
                '}';
    }
}

