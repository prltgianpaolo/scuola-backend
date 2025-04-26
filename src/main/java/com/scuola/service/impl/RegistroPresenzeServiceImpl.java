package com.scuola.service.impl;

import com.scuola.model.RegistroPresenze;
import com.scuola.repository.RegistroPresenzeRepository;
import com.scuola.service.RegistroPresenzeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistroPresenzeServiceImpl implements RegistroPresenzeService {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(RegistroPresenzeServiceImpl.class);

    @Autowired
    private RegistroPresenzeRepository registroPresenzeRepository;

    @Override
    public List<RegistroPresenze> findAll() {
        log.info("Recupero lista di tutte le presenze");
        return registroPresenzeRepository.findAll();
    }

    @Override
    public Optional<RegistroPresenze> findById(Long id) {
        log.info("Recupero presenze con ID {}", id);
        return registroPresenzeRepository.findById(id);
    }

    @Override
    public RegistroPresenze save(RegistroPresenze registroPresenze) {
        log.info("Salvataggio nuova presenza: {}", registroPresenze.getStudente().getNome());
        return registroPresenzeRepository.save(registroPresenze);
    }

    @Override
    public RegistroPresenze update (Long id, RegistroPresenze registroPresenze) {
        log.info("Aggiornamento presenza con ID {}", id);
        Optional<RegistroPresenze> existing = registroPresenzeRepository.findById(id);
        if (existing.isPresent()) {
            RegistroPresenze toUpdate = existing.get();
            toUpdate.setPresente(registroPresenze.isPresente());
            toUpdate.setNote(registroPresenze.getNote());
            toUpdate.setStudente(registroPresenze.getStudente());
            toUpdate.setLezione(registroPresenze.getLezione());
            return registroPresenzeRepository.save(toUpdate);
        } else {
            throw new RuntimeException("RegistroPresenze non trovato con ID:" + id);
        }
    }

    @Override
    public void deleteById(Long id) {
        log.info("Eliminazione presenza con ID {}", id);
        registroPresenzeRepository.deleteById(id);
    }
}
