package co.edu.uniquindio.pr3.subastasUQ.mapping.dto;

import co.edu.uniquindio.pr3.subastasUQ.model.Anuncio;
import co.edu.uniquindio.pr3.subastasUQ.model.Comprador;

public record PujaDTO(
        Anuncio anuncio,
        Comprador comprador,
        Double valor,
        String fecha
) {
}
