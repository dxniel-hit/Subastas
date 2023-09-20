package co.edu.uniquindio.pr3.subastasUQ.model;

import co.edu.uniquindio.pr3.subastasUQ.model.enumerations.TipoUsuario;

public class Usuario extends Persona{

    //Atributos de la clase
    private SubastasQuindio subastasQuindio;
    private String usuario;
    private String contrasenia;
    private String email;
    private Boolean isAutenticado;
    private TipoUsuario tipoUsuario;



    //Constructores de la clase

    public Usuario() {

    }

    public Usuario(String nombres, String apellidos, String identificacion, Integer edad, SubastasQuindio subastasQuindio, String usuario, String contrasenia, String email, Boolean isAutenticado, TipoUsuario tipoUsuario) {
        super(nombres, apellidos, identificacion, edad);
        this.subastasQuindio = subastasQuindio;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.email = email;
        this.isAutenticado = isAutenticado;
        this.tipoUsuario = tipoUsuario;
    }

    //Getters y setters

    public SubastasQuindio getSubastasQuindio() {
        return subastasQuindio;
    }
    public void setSubastasQuindio(SubastasQuindio subastasQuindio) {
        this.subastasQuindio = subastasQuindio;
    }
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

