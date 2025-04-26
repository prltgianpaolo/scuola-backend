package com.scuola.service.impl;

import com.scuola.model.Valutazione;
import com.scuola.repository.ValutazioneRepository;
import com.scuola.service.ValutazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ValutazioneServiceImpl implements ValutazioneService {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ValutazioneServiceImpl.class);

    @Autowired
    private ValutazioneRepository valutazioneRepository;

    @Override
    public List<Valutazione> findAll() {
        log.info("Recupero lista di tutte le valutazioni");
        return valutazioneRepository.findAll();
    }

    @Override
    public Optional<Valutazione> findById(Long id) {
        log.info("Recupero valutazione con ID {}", id);
        return valutazioneRepository.findById(id);
    }

    @Override
    public Valutazione save(Valutazione valutazione) {
        log.info("Salvataggio nuova valutazione: {}", valutazione.getStudente().getNome());
        return valutazioneRepository.save(valutazione);
    }

    @Override
    public Valutazione update(Long id, Valutazione valutazione) {
        log.info("Aggiornamento valutazione con ID {}", id);
        Optional<Valutazione> existing = valutazioneRepository.findById(id);
        if (existing.isPresent()) {
            Valutazione toUpdate = existing.get();
            toUpdate.setVoto(valutazione.getVoto());
            toUpdate.setStudente(valutazione.getStudente());
            toUpdate.setEsame(valutazione.getEsame());
            return valutazioneRepository.save(toUpdate);
        } else {
            throw new RuntimeException("Valutazione non trovata con ID:" + id);
        }
    }

    @Override
    public void deleteById(Long id) {
        log.info("Eliminazione valutazione con ID {}", id);
        valutazioneRepository.deleteById(id);
    }
}
