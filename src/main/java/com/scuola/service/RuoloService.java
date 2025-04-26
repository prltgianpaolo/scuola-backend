package com.scuola.service;

import com.scuola.model.Ruolo;

import java.util.List;
import java.util.Optional;

public interface RuoloService {
    List<Ruolo> findAll();
    Optional<Ruolo> findById(Long id);
    Optional<Ruolo> findByNome(String nome);
    Ruolo save(Ruolo ruolo);
    Ruolo update(Long id, Ruolo ruolo);
    void deleteById(Long id);
}
