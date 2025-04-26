package com.scuola.service;

import com.scuola.model.Docente;

import java.util.List;
import java.util.Optional;

public interface DocenteService {
    List<Docente> findAll();
    Optional<Docente> findById(Long id);
    Docente save(Docente docente);
    Docente update(Long id, Docente docente);
    void deleteById(Long id);
}
