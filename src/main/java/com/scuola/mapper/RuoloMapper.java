package com.scuola.mapper;

import com.scuola.dto.RuoloDTO;
import com.scuola.dto.RuoloDetailDTO;
import com.scuola.model.Ruolo;

public class RuoloMapper {

    public static RuoloDTO toDTO(Ruolo ruolo) {
        RuoloDTO dto = new RuoloDTO();
        dto.setId(ruolo.getId());
        dto.setNome(ruolo.getNome());
        return dto;
    }

    public static Ruolo toEntity(RuoloDTO dto) {
        Ruolo ruolo = new Ruolo();
        ruolo.setId(dto.getId());
        ruolo.setNome(dto.getNome());
        return ruolo;
    }

    public static RuoloDetailDTO toDetailDTO(com.scuola.model.Ruolo ruolo) {
        RuoloDetailDTO dto = new RuoloDetailDTO();
        dto.setId(ruolo.getId());
        dto.setNome(ruolo.getNome());

        if (ruolo.getUtenti() != null) {
            dto.setUtenti(
                    ruolo.getUtenti().stream()
                            .map(UtenteMapper::toDTO)
                            .toList()
            );
        }

        return dto;
    }

}
