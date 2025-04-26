package com.scuola.mapper;

import com.scuola.dto.ValutazioneDTO;
import com.scuola.dto.ValutazioneDetailDTO;
import com.scuola.model.Esame;
import com.scuola.model.Studente;
import com.scuola.model.Valutazione;

public class ValutazioneMapper {

    public static ValutazioneDTO toDTO(Valutazione valutazione) {
        ValutazioneDTO dto = new ValutazioneDTO();
        dto.setId(valutazione.getId());
        dto.setVoto(valutazione.getVoto());
        dto.setStudenteId(valutazione.getStudente().getId());
        dto.setEsameId(valutazione.getEsame().getId());
        return dto;
    }

    public static Valutazione toEntity(ValutazioneDTO dto) {
        Valutazione valutazione = new Valutazione();
        valutazione.setId(dto.getId());
        valutazione.setVoto(dto.getVoto());

        Studente studente = new Studente();
        studente.setId(dto.getStudenteId());
        valutazione.setStudente(studente);

        Esame esame = new Esame();
        esame.setId(dto.getEsameId());
        valutazione.setEsame(esame);

        return valutazione;
    }

    public static ValutazioneDetailDTO toDetailDTO(com.scuola.model.Valutazione valutazione) {
        ValutazioneDetailDTO dto = new ValutazioneDetailDTO();
        dto.setId(valutazione.getId());
        dto.setVoto(valutazione.getVoto());

        if (valutazione.getStudente() != null) {
            dto.setStudente(StudenteMapper.toDTO(valutazione.getStudente()));
        }

        if (valutazione.getEsame() != null) {
            dto.setEsame(EsameMapper.toDTO(valutazione.getEsame()));
        }

        return dto;
    }

}
