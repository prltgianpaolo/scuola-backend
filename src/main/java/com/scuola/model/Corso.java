package com.scuola.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Corso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descrizione;
    private LocalDate dataInizio;
    private LocalDate dataFine;
    private int oreTotali;

    @ManyToMany
    @JoinTable(
            name = "corso_materia",
            joinColumns = @JoinColumn(name = "corso_id"),
            inverseJoinColumns = @JoinColumn(name = "materia_id")
    )
    private List<Materia> materie;

    @OneToMany(mappedBy = "corso")
    private List<Iscrizione> iscrizioni;

}
