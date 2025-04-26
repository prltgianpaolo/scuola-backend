package com.scuola.dto;

import lombok.Data;

import java.util.List;

@Data
public class DocenteDetailDTO {

    private Long id;
    private String nome;
    private String cognome;
    private String email;
    private String specializzazione;
    private List<MateriaDTO> materie;
}
