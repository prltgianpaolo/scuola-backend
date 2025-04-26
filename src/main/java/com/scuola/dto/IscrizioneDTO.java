package com.scuola.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class IscrizioneDTO {

    private Long id;
    private LocalDate dataIscrizione;
    private Long studenteId;
    private Long corsoId;
}
