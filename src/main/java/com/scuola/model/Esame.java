package com.scuola.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Esame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataEsame;
    private String descrizione;

    @ManyToOne
    @JoinColumn(name = "materia_id")
    private Materia materia;

    @OneToMany(mappedBy = "esame")
    private List<Valutazione> valutazioni;
}
