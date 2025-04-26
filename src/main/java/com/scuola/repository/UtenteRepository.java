package com.scuola.repository;

import com.scuola.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtenteRepository extends JpaRepository<Utente, Long> {
    Utente findByUsername(String username);
}
