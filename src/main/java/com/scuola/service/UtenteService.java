package com.scuola.service;

import com.scuola.model.Utente;

import java.util.List;
import java.util.Optional;

public interface UtenteService {
    List<Utente> findAll();
    Optional<Utente> findById(Long id);
    Optional<Utente> findByUsername(String username);
    Utente save(Utente utente);
    Utente update(Long id, Utente utente);
    void deleteById(Long id);
}
