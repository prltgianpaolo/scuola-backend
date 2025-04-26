package com.scuola.mapper;

import com.scuola.dto.DocenteDTO;
import com.scuola.dto.DocenteDetailDTO;
import com.scuola.model.Docente;
import com.scuola.model.Materia;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class DocenteMapper {

    public static DocenteDTO toDTO(Docente docente) {
        DocenteDTO dto = new DocenteDTO();
        dto.setId(docente.getId());
        dto.setNome(docente.getNome());
        dto.setCognome(docente.getCognome());
        dto.setEmail(docente.getEmail());
        dto.setSpecializzazione(docente.getSpecializzazione());

        if (docente.getMaterie() != null) {
            dto.setMaterieIds(
                    docente.getMaterie().stream()
                            .map(Materia::getId)
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }

    public static Docente toEntity(DocenteDTO dto) {
        Docente docente = new Docente();
        docente.setId(dto.getId());
        docente.setNome(dto.getNome());
        docente.setCognome(dto.getCognome());
        docente.setEmail(dto.getEmail());
        docente.setSpecializzazione(dto.getSpecializzazione());

        if (dto.getMaterieIds() != null) {
            docente.setMaterie(
                    new ArrayList<>(
                            dto.getMaterieIds().stream()
                                    .map(id -> {
                                        Materia materia = new Materia();
                                        materia.setId(id);
                                        return materia;
                                    })
                                    .toList()
                    )
            );
        }


        return docente;
    }

    public static DocenteDetailDTO toDetailDTO(com.scuola.model.Docente docente) {
        DocenteDetailDTO dto = new DocenteDetailDTO();
        dto.setId(docente.getId());
        dto.setNome(docente.getNome());
        dto.setCognome(docente.getCognome());
        dto.setEmail(docente.getEmail());
        dto.setSpecializzazione(docente.getSpecializzazione());

        if (docente.getMaterie() != null) {
            dto.setMaterie(
                    docente.getMaterie().stream()
                            .map(MateriaMapper::toDTO)
                            .toList()
            );
        }

        return dto;
    }

}
