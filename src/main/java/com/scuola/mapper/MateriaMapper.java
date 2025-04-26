package com.scuola.mapper;

import com.scuola.dto.MateriaDTO;
import com.scuola.dto.MateriaDetailDTO;
import com.scuola.model.Materia;

public class MateriaMapper {

    public static MateriaDTO toDTO(Materia materia) {
        MateriaDTO dto = new MateriaDTO();
        dto.setId(materia.getId());
        dto.setNome(materia.getNome());
        dto.setDescrizione(materia.getDescrizione());
        dto.setOre(materia.getOre());
        return dto;
    }

    public static Materia toEntity(MateriaDTO dto) {
        Materia materia = new Materia();
        materia.setId(dto.getId());
        materia.setNome(dto.getNome());
        materia.setDescrizione(dto.getDescrizione());
        materia.setOre(dto.getOre());
        return materia;
    }

    public static MateriaDetailDTO toDetailDTO(com.scuola.model.Materia materia) {
        MateriaDetailDTO dto = new MateriaDetailDTO();
        dto.setId(materia.getId());
        dto.setNome(materia.getNome());
        dto.setDescrizione(materia.getDescrizione());
        dto.setOre(materia.getOre());

        if (materia.getDocente() != null) {
            dto.setDocente(DocenteMapper.toDTO(materia.getDocente()));
        }

        if (materia.getCorsi() != null) {
            dto.setCorsi(
                    materia.getCorsi().stream()
                            .map(CorsoMapper::toDTO)
                            .toList()
            );
        }

        return dto;
    }

}

