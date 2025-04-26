package com.scuola.service;

import com.scuola.model.Iscrizione;

import java.util.List;
import java.util.Optional;

public interface IscrizioneService {
    List<Iscrizione> findAll();
    Optional<Iscrizione> findById(Long id);
    Iscrizione save(Iscrizione iscrizione);
    Iscrizione update(Long id, Iscrizione iscrizione);
    void deleteById(Long id);
}
