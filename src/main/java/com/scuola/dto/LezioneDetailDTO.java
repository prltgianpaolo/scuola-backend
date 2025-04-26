package com.scuola.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class LezioneDetailDTO {

    private Long id;
    private LocalDate data;
    private LocalTime orarioInizio;
    private LocalTime orarioFine;
    private String argomento;
    private MateriaDTO materia;
    private DocenteDTO docente;
    private AulaDTO aula;
    private List<RegistroPresenzeDTO> presenze;
}
