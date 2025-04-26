package com.scuola.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class LezioneDTO {

    private Long id;
    private LocalDate data;
    private LocalTime orarioInizio;
    private LocalTime orarioFine;
    private String argomento;
    private Long materiaId;
    private Long docenteId;
    private Long aulaId;
}
