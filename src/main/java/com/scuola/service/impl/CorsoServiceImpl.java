package com.scuola.service.impl;

import com.scuola.model.Corso;
import com.scuola.repository.CorsoRepository;
import com.scuola.service.CorsoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CorsoServiceImpl implements CorsoService {

    private static final Logger log = LoggerFactory.getLogger(CorsoServiceImpl.class);

    @Autowired
    private CorsoRepository corsoRepository;

    @Override
    public List<Corso> findAll() {
        log.info("Recupero lista di tutti i corsi");
        return corsoRepository.findAll();
    }

    @Override
    public Optional<Corso> findById(Long id) {
        log.info("Recupero corso con ID {}", id);
        return corsoRepository.findById(id);
    }

    @Override
    public Corso save(Corso corso) {
        log.info("Salvataggio nuovo corso: {}", corso.getNome());
        return corsoRepository.save(corso);
    }

    @Override
    public Corso update(Long id, Corso corso) {
        log.info("Aggiornamento corso con ID {}", id);
        Optional<Corso> existing = corsoRepository.findById(id);
        if (existing.isPresent()) {
            Corso toUpdate = existing.get();
            toUpdate.setNome(corso.getNome());
            toUpdate.setDescrizione(corso.getDescrizione());
            toUpdate.setDataInizio(corso.getDataInizio());
            toUpdate.setDataFine(corso.getDataFine());
            toUpdate.setOreTotali(corso.getOreTotali());
            toUpdate.setMaterie(corso.getMaterie());
            return corsoRepository.save(toUpdate);
        } else {
            throw new RuntimeException("Corso non trovato con ID:" + id);
        }
    }

    @Override
    public void deleteById(Long id) {
        log.info("Eliminazione corso con ID {}", id);
        corsoRepository.deleteById(id);
    }
}
