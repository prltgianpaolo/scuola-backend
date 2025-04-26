package com.scuola.dto;

import lombok.Data;

import java.util.List;

@Data
public class MateriaDetailDTO {

    private Long id;
    private String nome;
    private String descrizione;
    private int ore;
    private DocenteDTO docente;
    private List<CorsoDTO> corsi;
}
