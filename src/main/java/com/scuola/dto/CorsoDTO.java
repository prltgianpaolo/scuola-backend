package com.scuola.dto;

import lombok.Data;


import java.time.LocalDate;
import java.util.List;

@Data
public class CorsoDTO {

    private Long id;
    private String nome;
    private String descrizione;
    private LocalDate dataInizio;
    private LocalDate dataFine;
    private int oreTotali;
    private List<Long> materieIds;
}
