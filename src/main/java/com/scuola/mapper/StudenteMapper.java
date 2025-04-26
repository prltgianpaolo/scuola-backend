package com.scuola.mapper;

import com.scuola.dto.StudenteDTO;
import com.scuola.dto.StudenteDetailDTO;
import com.scuola.model.Studente;

import java.util.stream.Collectors;

public class StudenteMapper {

    public static StudenteDTO toDTO(Studente studente) {
        StudenteDTO dto = new StudenteDTO();
        dto.setId(studente.getId());
        dto.setNome(studente.getNome());
        dto.setCognome(studente.getCognome());
        dto.setEmail(studente.getEmail());
        dto.setDatanascita(studente.getDatanascita());
        dto.setTelefono(studente.getTelefono());
        return dto;
    }

    public static Studente toEntity(StudenteDTO dto) {
        Studente studente = new Studente();
        studente.setId(dto.getId());
        studente.setNome(dto.getNome());
        studente.setCognome(dto.getCognome());
        studente.setEmail(dto.getEmail());
        studente.setDatanascita(dto.getDatanascita());
        studente.setTelefono(dto.getTelefono());
        return studente;
    }

    public static StudenteDetailDTO toDetailDTO(Studente studente) {
        StudenteDetailDTO dto = new StudenteDetailDTO();
        dto.setId(studente.getId());
        dto.setNome(studente.getNome());
        dto.setCognome(studente.getCognome());
        dto.setEmail(studente.getEmail());
        dto.setDataNascita(studente.getDatanascita());
        dto.setTelefono(studente.getTelefono());

        if (studente.getValutazioni() != null) {
            dto.setValutazioni(
                    studente.getValutazioni().stream()
                            .map(ValutazioneMapper::toDTO)
                            .collect(Collectors.toList())
            );
        }

        if (studente.getIscrizioni() != null) {
            dto.setIscrizioni(
                    studente.getIscrizioni().stream()
                            .map(IscrizioneMapper::toDTO)
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }
}
