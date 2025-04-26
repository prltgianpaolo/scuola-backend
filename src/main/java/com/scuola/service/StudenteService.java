package com.scuola.service;

import com.scuola.model.Studente;

import java.util.List;
import java.util.Optional;

public interface StudenteService {
    List<Studente> findAll();
    Optional<Studente> findById(Long id);
    Studente save(Studente studente);
    Studente update(Long id, Studente studente);
    void deleteById(Long id);
}
