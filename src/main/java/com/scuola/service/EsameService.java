package com.scuola.service;

import com.scuola.model.Esame;

import java.util.List;
import java.util.Optional;

public interface EsameService {
    List<Esame> findAll();
    Optional<Esame> findById(Long id);
    Esame save(Esame esame);
    Esame update(Long id, Esame esame);
    void deleteById(Long id);
}
