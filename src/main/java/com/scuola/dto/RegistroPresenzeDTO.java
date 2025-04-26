package com.scuola.dto;

import lombok.Data;

@Data
public class RegistroPresenzeDTO {

    private Long id;
    private boolean presente;
    private String note;
    private Long studenteId;
    private Long lezioneId;
}
