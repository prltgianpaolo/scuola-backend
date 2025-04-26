package com.scuola.mapper;

import com.scuola.dto.CorsoDTO;
import com.scuola.dto.CorsoDetailDTO;
import com.scuola.model.Corso;
import com.scuola.model.Materia;

import java.util.ArrayList;
import java.util.List;

public class CorsoMapper {

    public static CorsoDTO toDTO(Corso corso) {
        CorsoDTO dto = new CorsoDTO();
        dto.setId(corso.getId());
        dto.setNome(corso.getNome());
        dto.setDescrizione(corso.getDescrizione());
        dto.setDataInizio(corso.getDataInizio());
        dto.setDataFine(corso.getDataFine());
        dto.setOreTotali(corso.getOreTotali());

        if (corso.getMaterie() != null) {
            dto.setMaterieIds(
                    corso.getMaterie().stream()
                            .map(Materia::getId)
                            .toList()
            );
        }

        return dto;
    }

    public static Corso toEntity(CorsoDTO dto) {
        Corso corso = new Corso();
        corso.setId(dto.getId());
        corso.setNome(dto.getNome());
        corso.setDescrizione(dto.getDescrizione());
        corso.setDataInizio(dto.getDataInizio());
        corso.setDataFine(dto.getDataFine());
        corso.setOreTotali(dto.getOreTotali());

        if (dto.getMaterieIds() != null) {
            corso.setMaterie(
                    new ArrayList<>(
                            dto.getMaterieIds().stream()
                                    .map(id -> {
                                        Materia m = new Materia();
                                        m.setId(id);
                                        return m;
                                    })
                                    .toList()
                    )
            );
        }

        return corso;
    }

    public static CorsoDetailDTO toDetailDTO(com.scuola.model.Corso corso) {
        CorsoDetailDTO dto = new CorsoDetailDTO();
        dto.setId(corso.getId());
        dto.setNome(corso.getNome());
        dto.setDescrizione(corso.getDescrizione());
        dto.setDataInizio(corso.getDataInizio());
        dto.setDataFine(corso.getDataFine());
        dto.setOreTotali(corso.getOreTotali());

        if (corso.getMaterie() != null) {
            dto.setMaterie(
                    corso.getMaterie().stream()
                            .map(MateriaMapper::toDTO)
                            .toList()
            );
        }

        if (corso.getIscrizioni() != null) {
            dto.setIscrizioni(
                    corso.getIscrizioni().stream()
                            .map(IscrizioneMapper::toDTO)
                            .toList()
            );
        }

        return dto;
    }


}
