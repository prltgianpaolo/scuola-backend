package com.scuola.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class StudenteDetailDTO {

    private Long id;
    private String nome;
    private String cognome;
    private String email;
    private LocalDate dataNascita;
    private String telefono;
    private List<ValutazioneDTO> valutazioni;
    private List<IscrizioneDTO> iscrizioni;

}
