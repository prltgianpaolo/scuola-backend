package com.scuola.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class EsameDetailDTO {

    private Long id;
    private LocalDate dataEsame;
    private String descrizione;
    private MateriaDTO materia;
    private List<ValutazioneDTO> valutazioni;
}
