package com.scuola.mapper;

import com.scuola.dto.LezioneDTO;
import com.scuola.dto.LezioneDetailDTO;
import com.scuola.model.Aula;
import com.scuola.model.Docente;
import com.scuola.model.Lezione;
import com.scuola.model.Materia;

public class LezioneMapper {

    public static LezioneDTO toDTO(Lezione lezione) {
        LezioneDTO dto = new LezioneDTO();
        dto.setId(lezione.getId());
        dto.setData(lezione.getData());
        dto.setOrarioInizio(lezione.getOrarioInizio());
        dto.setOrarioFine(lezione.getOrarioFine());
        dto.setArgomento(lezione.getArgomento());
        dto.setDocenteId(lezione.getDocente().getId());
        dto.setMateriaId(lezione.getMateria().getId());
        dto.setAulaId(lezione.getAula().getId());
        return dto;
    }

    public static Lezione toEntity(LezioneDTO dto) {
        Lezione lezione = new Lezione();
        lezione.setId(dto.getId());
        lezione.setData(dto.getData());
        lezione.setOrarioInizio(dto.getOrarioInizio());
        lezione.setOrarioFine(dto.getOrarioFine());
        lezione.setArgomento(dto.getArgomento());

        Docente docente = new Docente();
        docente.setId(dto.getDocenteId());
        lezione.setDocente(docente);

        Materia materia = new Materia();
        materia.setId(dto.getMateriaId());
        lezione.setMateria(materia);

        Aula aula = new Aula();
        aula.setId(dto.getAulaId());
        lezione.setAula(aula);

        return lezione;
    }

    public static LezioneDetailDTO toDetailDTO(com.scuola.model.Lezione lezione) {
        LezioneDetailDTO dto = new LezioneDetailDTO();
        dto.setId(lezione.getId());
        dto.setData(lezione.getData());
        dto.setOrarioInizio(lezione.getOrarioInizio());
        dto.setOrarioFine(lezione.getOrarioFine());
        dto.setArgomento(lezione.getArgomento());

        if (lezione.getDocente() != null) {
            dto.setDocente(DocenteMapper.toDTO(lezione.getDocente()));
        }

        if (lezione.getMateria() != null) {
            dto.setMateria(MateriaMapper.toDTO(lezione.getMateria()));
        }

        if (lezione.getAula() != null) {
            dto.setAula(AulaMapper.toDTO(lezione.getAula()));
        }

        if (lezione.getPresenze() != null) {
            dto.setPresenze(
                    lezione.getPresenze().stream()
                            .map(RegistroPresenzeMapper::toDTO)
                            .toList()
            );
        }

        return dto;
    }

}
