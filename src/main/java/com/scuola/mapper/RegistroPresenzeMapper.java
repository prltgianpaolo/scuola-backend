package com.scuola.mapper;

import com.scuola.dto.RegistroPresenzeDTO;
import com.scuola.dto.RegistroPresenzeDetailDTO;
import com.scuola.model.Lezione;
import com.scuola.model.RegistroPresenze;
import com.scuola.model.Studente;

public class RegistroPresenzeMapper {

        public static RegistroPresenzeDTO toDTO(RegistroPresenze rp) {
            RegistroPresenzeDTO dto = new RegistroPresenzeDTO();
            dto.setId(rp.getId());
            dto.setPresente(rp.isPresente());
            dto.setNote(rp.getNote());
            dto.setStudenteId(rp.getStudente().getId());
            dto.setLezioneId(rp.getLezione().getId());
            return dto;
        }

        public static RegistroPresenze toEntity(RegistroPresenzeDTO dto) {
            RegistroPresenze rp = new RegistroPresenze();
            rp.setId(dto.getId());
            rp.setPresente(dto.isPresente());
            rp.setNote(dto.getNote());

            Studente studente = new Studente();
            studente.setId(dto.getStudenteId());
            rp.setStudente(studente);

            Lezione lezione = new Lezione();
            lezione.setId(dto.getLezioneId());
            rp.setLezione(lezione);

            return rp;
        }

    public static RegistroPresenzeDetailDTO toDetailDTO(RegistroPresenze presenze) {
        RegistroPresenzeDetailDTO dto = new RegistroPresenzeDetailDTO();
        dto.setId(presenze.getId());
        dto.setPresente(presenze.isPresente());
        dto.setNote(presenze.getNote());

        if (presenze.getStudente() != null) {
            dto.setStudente(StudenteMapper.toDTO(presenze.getStudente()));
        }

        if (presenze.getLezione() != null) {
            dto.setLezione(LezioneMapper.toDTO(presenze.getLezione()));
        }

        return dto;
    }

}

