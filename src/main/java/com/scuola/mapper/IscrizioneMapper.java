package com.scuola.mapper;

import com.scuola.dto.IscrizioneDTO;
import com.scuola.dto.IscrizioneDetailDTO;
import com.scuola.model.Corso;
import com.scuola.model.Iscrizione;
import com.scuola.model.Studente;

public class IscrizioneMapper {

    public static IscrizioneDTO toDTO(Iscrizione iscrizione) {
        IscrizioneDTO dto = new IscrizioneDTO();
        dto.setId(iscrizione.getId());
        dto.setDataIscrizione(iscrizione.getDataIscrizione());
        dto.setStudenteId(iscrizione.getStudente().getId());
        dto.setCorsoId(iscrizione.getCorso().getId());
        return dto;
    }

    public static Iscrizione toEntity(IscrizioneDTO dto) {
        Iscrizione iscrizione = new Iscrizione();
        iscrizione.setId(dto.getId());
        iscrizione.setDataIscrizione(dto.getDataIscrizione());

        Studente studente = new Studente();
        studente.setId(dto.getStudenteId());
        iscrizione.setStudente(studente);

        Corso corso = new Corso();
        corso.setId(dto.getCorsoId());
        iscrizione.setCorso(corso);

        return iscrizione;
    }

    public static IscrizioneDetailDTO toDetailDTO(Iscrizione iscrizione) {
        IscrizioneDetailDTO dto = new IscrizioneDetailDTO();
        dto.setId(iscrizione.getId());
        dto.setDataIscrizione(iscrizione.getDataIscrizione());

        if (iscrizione.getStudente() != null) {
            dto.setStudente(StudenteMapper.toDTO(iscrizione.getStudente()));
        }

        if (iscrizione.getCorso() != null) {
            dto.setCorso(CorsoMapper.toDTO(iscrizione.getCorso()));
        }

        return dto;
    }

}
