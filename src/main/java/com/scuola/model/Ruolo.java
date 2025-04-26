package com.scuola.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Ruolo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome; // STUDENTE - DOCENTE - ADMIN

    @OneToMany(mappedBy = "ruolo")
    private List<Utente> utenti;
}
