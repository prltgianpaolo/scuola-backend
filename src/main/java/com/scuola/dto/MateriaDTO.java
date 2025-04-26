package com.scuola.dto;

import lombok.Data;

@Data
public class MateriaDTO {

    private Long id;
    private String nome;
    private String descrizione;
    private int ore;
}
