package com.scuola.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StudenteDTO {

    private Long id;
    private String nome;
    private String cognome;
    private String email;
    private LocalDate datanascita;
    private String telefono;
}
