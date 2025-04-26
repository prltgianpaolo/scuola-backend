package com.scuola.mapper;

import com.scuola.dto.UtenteDTO;
import com.scuola.dto.UtenteDetailDTO;
import com.scuola.model.Ruolo;
import com.scuola.model.Utente;

public class UtenteMapper {

    public static UtenteDTO toDTO(Utente utente) {
        UtenteDTO dto = new UtenteDTO();
        dto.setId(utente.getId());
        dto.setUsername(utente.getUsername());
        dto.setPassword(utente.getPassword());
        dto.setRuoloId(utente.getRuolo().getId());
        return dto;
    }

    public static Utente toEntity(UtenteDTO dto) {
        Utente utente = new Utente();
        utente.setId(dto.getId());
        utente.setUsername(dto.getUsername());
        utente.setPassword(dto.getPassword());

        Ruolo ruolo = new Ruolo();
        ruolo.setId(dto.getRuoloId());
        utente.setRuolo(ruolo);

        return utente;
    }

    public static UtenteDetailDTO toDetailDTO(com.scuola.model.Utente utente) {
        UtenteDetailDTO dto = new UtenteDetailDTO();
        dto.setId(utente.getId());
        dto.setUsername(utente.getUsername());
        dto.setPassword(utente.getPassword());

        if (utente.getRuolo() != null) {
            dto.setRuolo(RuoloMapper.toDTO(utente.getRuolo()));
        }

        return dto;
    }

}
