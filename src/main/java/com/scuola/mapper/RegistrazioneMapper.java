package com.scuola.mapper;

import com.scuola.dto.RegistrazioneDTO;
import com.scuola.model.Ruolo;
import com.scuola.model.Utente;

public class RegistrazioneMapper {

    public static Utente toEntity(RegistrazioneDTO dto) {
        Utente utente = new Utente();
        utente.setUsername(dto.getUsername());
        utente.setPassword(dto.getPassword());

        Ruolo ruolo = new Ruolo();
        ruolo.setId(dto.getRuoloId());
        utente.setRuolo(ruolo);

        return utente;
    }
}
