package com.scuola.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Valutazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double voto;

    @ManyToOne
    @JoinColumn(name = "studente_id")
    private Studente studente;

    @ManyToOne
    @JoinColumn(name = "esame_id")
    private Esame esame;
}
