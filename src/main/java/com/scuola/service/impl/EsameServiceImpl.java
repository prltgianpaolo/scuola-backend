package com.scuola.service.impl;

import com.scuola.model.Esame;
import com.scuola.repository.EsameRepository;
import com.scuola.service.EsameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EsameServiceImpl implements EsameService {

    private static final Logger log = LoggerFactory.getLogger(EsameServiceImpl.class);

    @Autowired
    private EsameRepository esameRepository;

    @Override
    public List<Esame> findAll() {
        log.info("Recupero lista di tutti gli esami");
        return esameRepository.findAll();
    }

    @Override
    public Optional<Esame> findById(Long id) {
        log.info("Recupero esame con ID {}", id);
        return esameRepository.findById(id);
    }

    @Override
    public Esame save(Esame esame) {
        log.info("Salvataggio nuovo esame: {}", esame.getDescrizione());
        return esameRepository.save(esame);
    }

    @Override
    public Esame update(Long id, Esame esame) {
        log.info("Aggiornamento esame con ID {}", id);
        Optional<Esame> existing = esameRepository.findById(id);
        if (existing.isPresent()) {
            Esame toUpdate = existing.get();
            toUpdate.setDataEsame(esame.getDataEsame());
            toUpdate.setDescrizione(esame.getDescrizione());
            toUpdate.setMateria(esame.getMateria());
            return esameRepository.save(toUpdate);
        } else {
            throw new RuntimeException("Esame non trovato con ID:" + id);
        }
    }

    @Override
    public void deleteById(Long id) {
        log.info("Eliminazione esame con ID {}", id);
        esameRepository.deleteById(id);
    }
}
