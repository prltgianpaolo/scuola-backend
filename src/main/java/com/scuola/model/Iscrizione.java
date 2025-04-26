package com.scuola.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Iscrizione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataIscrizione;

    @ManyToOne
    @JoinColumn(name = "studente_id")
    private Studente studente;

    @ManyToOne
    @JoinColumn(name = "corso_id")
    private Corso corso;
}
