package com.scuola.service;

import com.scuola.model.Aula;

import java.util.List;
import java.util.Optional;

public interface AulaService {
    List<Aula> findAll();
    Optional<Aula> findById(Long id);
    Aula save(Aula aula);
    Aula update(Long id, Aula aula);
    void deleteById(Long id);
}
