package com.scuola.service.impl;

import com.scuola.model.Studente;
import com.scuola.repository.StudenteRepository;
import com.scuola.service.StudenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudenteServiceImpl implements StudenteService {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(StudenteServiceImpl.class);

    @Autowired
    private StudenteRepository studenteRepository;

    @Override
    public List<Studente> findAll() {
        log.info("Recupero lista di tutti i studenti");
        return studenteRepository.findAll();
    }

    @Override
    public Optional<Studente> findById(Long id) {
        log.info("Recupero studente con ID {}", id);
        return studenteRepository.findById(id);
    }

    @Override
    public Studente save(Studente studente) {
        log.info("Salvataggio nuovo studente: {}", studente.getNome());
        return studenteRepository.save(studente);
    }

    @Override
    public void deleteById(Long id) {
        log.info("Eliminazione studente con ID {}", id);
        studenteRepository.deleteById(id);
    }

    @Override
    public Studente update(Long id, Studente studente) {
        log.info("Aggiornamento studente con ID {}", id);
        Optional<Studente> existing = studenteRepository.findById(id);
        if (existing.isPresent()) {
            Studente toUpdate = existing.get();
            toUpdate.setNome(studente.getNome());
            toUpdate.setCognome(studente.getCognome());
            toUpdate.setEmail(studente.getEmail());
            toUpdate.setDatanascita(studente.getDatanascita());
            toUpdate.setTelefono(studente.getTelefono());
            return studenteRepository.save(toUpdate);
        } else {
            throw new RuntimeException("Studente non trovato con ID:" + id);
        }
    }

}
