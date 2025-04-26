package com.scuola.service.impl;

import com.scuola.model.Aula;
import com.scuola.repository.AulaRepository;
import com.scuola.service.AulaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AulaServiceImpl implements AulaService {

    private static final Logger log = LoggerFactory.getLogger(AulaServiceImpl.class);


    @Autowired
    private AulaRepository aulaRepository;

    @Override
    public List<Aula> findAll() {
        log.info("Recupero lista di tutte le aule");
        return aulaRepository.findAll();
    }

    @Override
    public Optional<Aula> findById(Long id) {
        log.info("Recupero aula con ID {}", id);
        return aulaRepository.findById(id);
    }

    @Override
    public Aula save(Aula aula) {
        log.info("Salvataggio nuova aula: {}", aula.getNome());
        return aulaRepository.save(aula);
    }

    @Override
    public Aula update(Long id, Aula aula) {
        log.info("Aggiornamento aula con ID {}", id);
        Optional<Aula> existing = aulaRepository.findById(id);
        if (existing.isPresent()) {
            Aula toUpdate = existing.get();
            toUpdate.setNome(aula.getNome());
            toUpdate.setCapienza(aula.getCapienza());
            toUpdate.setPiano(aula.getPiano());
            return aulaRepository.save(toUpdate);
        } else {
            throw new RuntimeException("Aula non trovata con ID:" + id);
        }
    }

    @Override
    public void deleteById(Long id) {
        log.info("Eliminazione aula con ID {}", id);
        aulaRepository.deleteById(id);
    }
}
