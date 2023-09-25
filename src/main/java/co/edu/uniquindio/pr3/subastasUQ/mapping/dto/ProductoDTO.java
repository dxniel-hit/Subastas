package co.edu.uniquindio.pr3.subastasUQ.mapping.dto;

import co.edu.uniquindio.pr3.subastasUQ.model.enumerations.*;
import javafx.scene.image.*;


//Faltan isAnunciado que es boolean y existencias que a lo mejor meto.
public record ProductoDTO(

        String codigo,
        String nombre,
        String descripcion,
        Double valorInicial,
        TipoProducto tipoProducto) {

}
