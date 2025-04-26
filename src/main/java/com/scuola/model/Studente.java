package com.scuola.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Studente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cognome;
    private String email;
    private LocalDate datanascita;
    private String telefono;

    @OneToMany(mappedBy = "studente")
    private List<Iscrizione> iscrizioni;

    @OneToMany(mappedBy = "studente")
    private List<Valutazione> valutazioni;


}
