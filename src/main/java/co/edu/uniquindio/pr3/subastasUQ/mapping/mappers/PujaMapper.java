package co.edu.uniquindio.pr3.subastasUQ.mapping.mappers;

import co.edu.uniquindio.pr3.subastasUQ.mapping.dto.AnuncioDTO;
import co.edu.uniquindio.pr3.subastasUQ.mapping.dto.PujaDTO;
import co.edu.uniquindio.pr3.subastasUQ.model.Anuncio;
import co.edu.uniquindio.pr3.subastasUQ.model.Puja;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PujaMapper {
    PujaMapper INSTANCE = Mappers.getMapper(PujaMapper.class);

    @Named("PujaToPujaDTO")
    PujaDTO PujaToPujaDTO(Puja puja);
    Puja PujaDTOtoPuja(PujaDTO dto);

    @IterableMapping(qualifiedByName = "PujaToPujaDTO")
    List<PujaDTO> getPujasDTO(List<Puja> listaPujas);


}
