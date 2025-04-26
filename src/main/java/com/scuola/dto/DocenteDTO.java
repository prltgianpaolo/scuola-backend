package com.scuola.dto;

import lombok.Data;

import java.util.List;

@Data
public class DocenteDTO {

    private Long id;
    private String nome;
    private String cognome;
    private String email;
    private String specializzazione;
    private List<Long> materieIds;
}
