package com.scuola.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EsameDTO {

    private Long id;
    private String descrizione;
    private LocalDate dataEsame;
}
