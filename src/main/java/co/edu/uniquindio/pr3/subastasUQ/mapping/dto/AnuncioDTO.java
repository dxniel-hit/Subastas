package co.edu.uniquindio.pr3.subastasUQ.mapping.dto;

import co.edu.uniquindio.pr3.subastasUQ.model.Compra;
import co.edu.uniquindio.pr3.subastasUQ.model.Producto;
import co.edu.uniquindio.pr3.subastasUQ.model.Puja;

import java.util.List;

public record AnuncioDTO(
        String codigo,
        String fechaInicio,
        String fechaFinal,
        String nombreAnunciante,
        Producto producto
) {

}
