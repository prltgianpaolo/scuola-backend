package com.scuola.service;

import com.scuola.model.Valutazione;

import java.util.List;
import java.util.Optional;

public interface ValutazioneService {
    List<Valutazione> findAll();
    Optional<Valutazione> findById(Long id);
    Valutazione save(Valutazione valutazione);
    Valutazione update(Long id, Valutazione valutazione);
    void deleteById(Long id);
}
