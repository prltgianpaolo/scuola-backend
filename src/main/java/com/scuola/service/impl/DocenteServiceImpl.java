package com.scuola.service.impl;

import com.scuola.model.Docente;
import com.scuola.model.Materia;
import com.scuola.repository.DocenteRepository;
import com.scuola.repository.MateriaRepository;
import com.scuola.service.DocenteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DocenteServiceImpl implements DocenteService {

    private static final Logger log = LoggerFactory.getLogger(DocenteServiceImpl.class);

    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private MateriaRepository materiaRepository;

    @Override
    public List<Docente> findAll() {
        log.info("Recupero lista di tutti i docenti");
        return docenteRepository.findAll();
    }

    @Override
    public Optional<Docente> findById(Long id) {
        log.info("Recupero docente con ID {}", id);
        return docenteRepository.findById(id);
    }

    @Override
    public Docente save(Docente docente) {
        log.info("Salvataggio nuovo docente: {}", docente.getNome());
        return docenteRepository.save(docente);
    }

    @Override
    public Docente update(Long id, Docente docente) {
        log.info("Aggiornamento docente con ID {}", id);
        Optional<Docente> existing = docenteRepository.findById(id);
        if (existing.isPresent()) {
            Docente toUpdate = existing.get();
            toUpdate.setNome(docente.getNome());
            toUpdate.setCognome(docente.getCognome());
            toUpdate.setEmail(docente.getEmail());
            toUpdate.setSpecializzazione(docente.getSpecializzazione());

            if (docente.getMaterie() != null) {
                List<Materia> materieAggiornate = new ArrayList<>();
                for (Materia materia : docente.getMaterie()) {
                    Materia materiaEsistente = materiaRepository.findById(materia.getId())
                            .orElseThrow(() -> new RuntimeException("Materia non trovata con ID: " + materia.getId()));
                    materiaEsistente.setDocente(toUpdate);
                    materiaRepository.save(materiaEsistente);
                    materieAggiornate.add(materiaEsistente);
                }
                toUpdate.setMaterie(materieAggiornate);
            }

            return docenteRepository.save(toUpdate);

        } else {
            throw new RuntimeException("Docente non trovato con ID:" + id);
        }
    }

    @Override
    public void deleteById(Long id) {
        log.info("Eliminazione docente con ID {}", id);
        docenteRepository.deleteById(id);
    }
}
