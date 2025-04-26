package com.scuola.service.impl;

import com.scuola.model.Lezione;
import com.scuola.repository.LezioneRepository;
import com.scuola.service.LezioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LezioneServiceImpl implements LezioneService {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(LezioneServiceImpl.class);

    @Autowired
    private LezioneRepository lezioneRepository;

    @Override
    public List<Lezione> findAll() {
        log.info("Recupero lista di tutte le lezioni");
        return lezioneRepository.findAll();
    }

    @Override
    public Optional<Lezione> findById(Long id) {
        log.info("Recupero lezione con ID {}", id);
        return lezioneRepository.findById(id);
    }

    @Override
    public Lezione save(Lezione lezione) {
        log.info("Salvataggio nuova lezione: {}", lezione.getData());
        return lezioneRepository.save(lezione);
    }

    @Override
    public Lezione update(Long id, Lezione lezione) {
        log.info("Aggiornamento lezione con ID {}", id);
        Optional<Lezione> existing = lezioneRepository.findById(id);
        if (existing.isPresent()) {
            Lezione toUpdate = existing.get();
            toUpdate.setData(lezione.getData());
            toUpdate.setOrarioInizio(lezione.getOrarioInizio());
            toUpdate.setOrarioFine(lezione.getOrarioFine());
            toUpdate.setArgomento(lezione.getArgomento());
            toUpdate.setDocente(lezione.getDocente());
            toUpdate.setMateria(lezione.getMateria());
            toUpdate.setAula(lezione.getAula());
            return lezioneRepository.save(toUpdate);
        } else {
            throw new RuntimeException("Lezione non trovata con ID:" + id);
        }
    }

    @Override
    public void deleteById(Long id) {
        log.info("Eliminazione lezione con ID {}", id);
        lezioneRepository.deleteById(id);
    }
}
