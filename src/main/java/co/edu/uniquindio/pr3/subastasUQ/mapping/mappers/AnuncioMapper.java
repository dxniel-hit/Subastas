package co.edu.uniquindio.pr3.subastasUQ.mapping.mappers;

import co.edu.uniquindio.pr3.subastasUQ.mapping.dto.AnuncioDTO;
import co.edu.uniquindio.pr3.subastasUQ.mapping.dto.ProductoDTO;
import co.edu.uniquindio.pr3.subastasUQ.model.Anuncio;
import co.edu.uniquindio.pr3.subastasUQ.model.Producto;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AnuncioMapper {

    AnuncioMapper INSTANCE = Mappers.getMapper(AnuncioMapper.class);

    @Named("AnuncioToAnuncioDTO")
    AnuncioDTO anuncioToAnuncioDTO(Anuncio anuncio);
    Anuncio anuncioDTOtoAnuncio(AnuncioDTO dto);

    @IterableMapping(qualifiedByName = "AnuncioToAnuncioDTO")
    List<AnuncioDTO> getAnunciosDTO(List<Anuncio> listaAnuncios);

}
