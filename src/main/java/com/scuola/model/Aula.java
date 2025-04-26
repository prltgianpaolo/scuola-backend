package com.scuola.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private int capienza;
    private String piano;

    @OneToMany(mappedBy = "aula")
    private List<Lezione> lezioni;
}
