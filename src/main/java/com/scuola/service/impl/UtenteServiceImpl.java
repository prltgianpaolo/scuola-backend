package com.scuola.service.impl;

import com.scuola.model.Utente;
import com.scuola.repository.UtenteRepository;
import com.scuola.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtenteServiceImpl implements UtenteService {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(UtenteServiceImpl.class);

    @Autowired
    private UtenteRepository utenteRepository;

    @Override
    public List<Utente> findAll() {
        log.info("Recupero lista di tutti gli utenti");
        return utenteRepository.findAll();
    }

    @Override
    public Optional<Utente> findById(Long id) {
        log.info("Recupero utente con ID {}", id);
        return utenteRepository.findById(id);
    }

    @Override
    public Utente save(Utente utente) {
        log.info("Salvataggio nuovo utente: {}", utente.getUsername());
        return utenteRepository.save(utente);
    }

    @Override
    public Utente update(Long id, Utente utente) {
        log.info("Aggiornamento utente con ID {}", id);
        Optional<Utente> existing = utenteRepository.findById(id);
        if (existing.isPresent()) {
            Utente toUpdate = existing.get();
            toUpdate.setUsername(utente.getUsername());
            toUpdate.setPassword(utente.getPassword());
            toUpdate.setRuolo(utente.getRuolo());
            return utenteRepository.save(toUpdate);
        } else {
            throw new RuntimeException("Utente non trovato con ID:" + id);
        }
    }

    @Override
    public void deleteById(Long id) {
        log.info("Eliminazione utente con ID {}", id);
        utenteRepository.deleteById(id);
    }

    @Override
    public Optional<Utente> findByUsername(String username) {
        return Optional.ofNullable(utenteRepository.findByUsername(username));
    }
}
