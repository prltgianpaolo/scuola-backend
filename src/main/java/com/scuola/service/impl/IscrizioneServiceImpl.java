package com.scuola.service.impl;

import com.scuola.model.Iscrizione;
import com.scuola.repository.IscrizioneRepository;
import com.scuola.service.IscrizioneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IscrizioneServiceImpl implements IscrizioneService {

    private static final Logger log = LoggerFactory.getLogger(IscrizioneServiceImpl.class);

    @Autowired
    private IscrizioneRepository iscrizioneRepository;

    @Override
    public List<Iscrizione> findAll() {
        log.info("Recupero lista di tutte le iscrizioni");
        return iscrizioneRepository.findAll();
    }

    @Override
    public Optional<Iscrizione> findById(Long id) {
        log.info("Recupero iscrizione con ID {}", id);
        return iscrizioneRepository.findById(id);
    }

    @Override
    public Iscrizione save(Iscrizione iscrizione) {
        log.info("Salvataggio nuova iscrizione: {}", iscrizione.getStudente().getNome());
        return iscrizioneRepository.save(iscrizione);
    }

    @Override
    public Iscrizione update(Long id, Iscrizione iscrizione) {
        log.info("Aggiornamento iscrizione con ID {}", id);
        Optional<Iscrizione> existing = iscrizioneRepository.findById(id);
        if (existing.isPresent()) {
            Iscrizione toUpdate = existing.get();
            toUpdate.setDataIscrizione(iscrizione.getDataIscrizione());
            toUpdate.setStudente(iscrizione.getStudente());
            toUpdate.setCorso(iscrizione.getCorso());
            return iscrizioneRepository.save(toUpdate);
        } else {
            throw new RuntimeException("Iscrizione non trovata con ID:" + id);
        }
    }

    @Override
    public void deleteById(Long id) {
        log.info("Eliminazione iscrizione con ID {}", id);
        iscrizioneRepository.deleteById(id);
    }
}
