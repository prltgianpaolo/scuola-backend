package com.scuola.service;

import com.scuola.model.Materia;

import java.util.List;
import java.util.Optional;

public interface MateriaService {
    List<Materia> findAll();
    Optional<Materia> findById(Long id);
    Materia save(Materia materia);
    Materia update(Long id, Materia materia);
    void deleteById(Long id);
}
