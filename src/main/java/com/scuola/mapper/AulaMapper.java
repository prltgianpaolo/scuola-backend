package com.scuola.mapper;

import com.scuola.dto.AulaDTO;
import com.scuola.dto.AulaDetailDTO;
import com.scuola.model.Aula;

public class AulaMapper {

    public static AulaDTO toDTO(Aula aula) {
        AulaDTO dto = new AulaDTO();
        dto.setId(aula.getId());
        dto.setNome(aula.getNome());
        dto.setCapienza(aula.getCapienza());
        dto.setPiano(aula.getPiano());
        return dto;
    }

    public static Aula toEntity(AulaDTO dto) {
        Aula aula = new Aula();
        aula.setId(dto.getId());
        aula.setNome(dto.getNome());
        aula.setCapienza(dto.getCapienza());
        aula.setPiano(dto.getPiano());
        return aula;
    }

    public static AulaDetailDTO toDetailDTO(com.scuola.model.Aula aula) {
        AulaDetailDTO dto = new AulaDetailDTO();
        dto.setId(aula.getId());
        dto.setNome(aula.getNome());
        dto.setCapienza(aula.getCapienza());
        dto.setPiano(aula.getPiano());

        if (aula.getLezioni() != null) {
            dto.setLezioni(
                    aula.getLezioni().stream()
                            .map(LezioneMapper::toDTO)
                            .toList()
            );
        }

        return dto;
    }

}
