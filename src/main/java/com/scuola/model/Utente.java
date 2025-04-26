package com.scuola.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @ManyToOne
    @JoinColumn(name = "ruolo_id")
    private Ruolo ruolo;

}
