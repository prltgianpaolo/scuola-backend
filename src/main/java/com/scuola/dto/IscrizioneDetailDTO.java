package com.scuola.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class IscrizioneDetailDTO {

    private Long id;
    private LocalDate dataIscrizione;
    private StudenteDTO studente;
    private CorsoDTO corso;
}
