package com.scuola.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descrizione;
    private int ore;

    @ManyToOne
    @JoinColumn(name = "docente_id")
    private Docente docente;

    @ManyToMany(mappedBy = "materie")
    private List<Corso> corsi;

    @OneToMany(mappedBy = "materia")
    private List<Esame> esami;

}
