package com.scuola.dto;

import lombok.Data;

@Data
public class UtenteDetailDTO {

    private Long id;
    private String username;
    private String password;
    private RuoloDTO ruolo;
}
