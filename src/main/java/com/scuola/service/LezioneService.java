package com.scuola.service;

import com.scuola.model.Lezione;

import java.util.List;
import java.util.Optional;

public interface LezioneService {
    List<Lezione> findAll();
    Optional<Lezione> findById(Long id);
    Lezione save(Lezione lezione);
    Lezione update(Long id, Lezione lezione);
    void deleteById(Long id);
}
