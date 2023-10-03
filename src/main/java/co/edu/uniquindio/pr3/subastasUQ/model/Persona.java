package co.edu.uniquindio.pr3.subastasUQ.model;

import java.io.Serializable;

public abstract class Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    //Atributos de la clase
    private String nombres;
    private String apellidos;
    private String identificacion;
    private Integer edad;

    //Métodos constructores de la clase

    public Persona(String nombres, String apellidos, String identificacion, Integer edad) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.identificacion = identificacion;
        this.edad = edad;
    }

    public Persona() {
    }

    //Getters y setters

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    //toString()
    @Override
    public String toString() {
        return "Persona{" +
                "nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", identificacion='" + identificacion + '\'' +
                ", edad=" + edad +
                '}';
    }
}
