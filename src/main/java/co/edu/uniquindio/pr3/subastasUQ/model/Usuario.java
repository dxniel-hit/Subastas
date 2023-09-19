package com.uq.pr3.model;

import com.uq.pr3.model.enumerations.TipoUsuario;

public class Usuario {

    //Atributos de la clase
    private String usuario;
    private String contrasenia;
    private Boolean isAutenticado;
    private TipoUsuario tipoUsuario;
    private Anuncio anuncio;
    private SubastasQuindio subastasQuindio;



    //Constructores de la clase

    public Usuario() {

    }

    public Usuario(String usuario, String contrasenia, Boolean isAutenticado, TipoUsuario tipoUsuario) {
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
}

