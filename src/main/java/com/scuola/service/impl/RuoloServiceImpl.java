package com.scuola.service.impl;

import com.scuola.model.Ruolo;
import com.scuola.repository.RuoloRepository;
import com.scuola.service.RuoloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RuoloServiceImpl implements RuoloService {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(RuoloServiceImpl.class);

    @Autowired
    private RuoloRepository ruoloRepository;

    @Override
    public List<Ruolo> findAll() {
        log.info("Recupero lista di tutti i ruoli");
        return ruoloRepository.findAll();
    }

    @Override
    public Optional<Ruolo> findById(Long id) {
        log.info("Recupero ruolo con ID {}", id);
        return ruoloRepository.findById(id);
    }

    @Override
    public Optional<Ruolo> findByNome(String nome) {
        log.info("Recupero ruolo con nome {}", nome);
        return Optional.ofNullable(ruoloRepository.findByNome(nome));
    }

    @Override
    public Ruolo save(Ruolo ruolo) {
        log.info("Salvataggio nuovo ruolo: {}", ruolo.getNome());
        return ruoloRepository.save(ruolo);
    }

    @Override
    public Ruolo update(Long id, Ruolo ruolo) {
        log.info("Aggiornamento ruolo con ID {}", id);
        Optional<Ruolo> existing = ruoloRepository.findById(id);
        if (existing.isPresent()) {
            Ruolo toUpdate = existing.get();
            toUpdate.setNome(ruolo.getNome());
            return ruoloRepository.save(toUpdate);
        } else {
            throw new RuntimeException("Ruolo non trovato con ID:" + id);
        }
    }

    @Override
    public void deleteById(Long id) {
        log.info("Eliminazione ruolo con ID {}", id);
        ruoloRepository.deleteById(id);
    }
}
