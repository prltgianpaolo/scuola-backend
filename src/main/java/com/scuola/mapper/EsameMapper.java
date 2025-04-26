package com.scuola.mapper;

import com.scuola.dto.EsameDTO;
import com.scuola.dto.EsameDetailDTO;
import com.scuola.model.Esame;

public class EsameMapper {

    public static EsameDTO toDTO(Esame esame) {
        EsameDTO dto = new EsameDTO();
        dto.setId(esame.getId());
        dto.setDataEsame(esame.getDataEsame());
        dto.setDescrizione(esame.getDescrizione());
        return dto;
    }

    public static Esame toEntity(EsameDTO dto) {
        Esame esame = new Esame();
        esame.setId(dto.getId());
        esame.setDataEsame(dto.getDataEsame());
        esame.setDescrizione(dto.getDescrizione());
        return esame;
    }

    public static EsameDetailDTO toDetailDTO(com.scuola.model.Esame esame) {
        EsameDetailDTO dto = new EsameDetailDTO();
        dto.setId(esame.getId());
        dto.setDataEsame(esame.getDataEsame());
        dto.setDescrizione(esame.getDescrizione());

        if (esame.getMateria() != null) {
            dto.setMateria(MateriaMapper.toDTO(esame.getMateria()));
        }

        if (esame.getValutazioni() != null) {
            dto.setValutazioni(
                    esame.getValutazioni().stream()
                            .map(ValutazioneMapper::toDTO)
                            .toList()
            );
        }

        return dto;
    }

}
