package com.scuola.dto;

import lombok.Data;

@Data
public class RegistroPresenzeDetailDTO {

    private Long id;
    private boolean presente;
    private String note;
    private StudenteDTO studente;
    private LezioneDTO lezione;
}
