package com.scuola.service;

import com.scuola.model.Corso;

import java.util.List;
import java.util.Optional;

public interface CorsoService {
    List<Corso> findAll();
    Optional<Corso> findById(Long id);
    Corso save(Corso corso);
    Corso update(Long id, Corso corso);
    void deleteById(Long id);
}
