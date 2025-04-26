package com.scuola.dto;

import lombok.Data;

import java.util.List;

@Data
public class RuoloDetailDTO {

    private Long id;
    private String nome;
    private List<UtenteDTO> utenti;
}
