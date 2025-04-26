package com.scuola.dto;

import lombok.Data;

@Data
public class ValutazioneDTO {

    private Long id;
    private Double voto;
    private Long studenteId;
    private Long esameId;
}
