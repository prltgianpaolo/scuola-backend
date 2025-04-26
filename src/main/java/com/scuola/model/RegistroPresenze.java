package com.scuola.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class RegistroPresenze {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean presente;
    private String note;

    @ManyToOne
    @JoinColumn(name = "studente_id")
    private Studente studente;

    @ManyToOne
    @JoinColumn(name = "lezione_id")
    private Lezione lezione;

}
