package co.edu.uniquindio.pr3.subastasUQ.mapping.dto;

import co.edu.uniquindio.pr3.subastasUQ.model.enumerations.*;
import javafx.scene.image.*;

public record ProductoDTO(

        String codigo,
        String nombre,
        String descripcion,
        String image,
        Double valorInicial,
        TipoProducto tipoProducto,
        Boolean isAnunciado) {

}
