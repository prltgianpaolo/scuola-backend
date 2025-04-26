package com.scuola.dto;

import lombok.Data;

import java.util.List;

@Data
public class AulaDetailDTO {

    private Long id;
    private String nome;
    private int capienza;
    private String piano;
    private List<LezioneDTO> lezioni;
     
}
