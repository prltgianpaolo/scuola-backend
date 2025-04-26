package com.scuola.dto;

import lombok.Data;

@Data
public class ValutazioneDetailDTO {

    private Long id;
    private Double voto;
    private StudenteDTO studente;
    private EsameDTO esame;
}
