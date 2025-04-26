package com.scuola.dto;

import lombok.Data;

@Data
public class UtenteDTO {

    private Long id;
    private String username;
    private String password;
    private Long ruoloId;
}
