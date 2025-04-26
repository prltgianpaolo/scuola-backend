package com.scuola.service.impl;

import com.scuola.model.Materia;
import com.scuola.repository.MateriaRepository;
import com.scuola.service.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MateriaServiceImpl implements MateriaService {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(MateriaServiceImpl.class);

    @Autowired
    private MateriaRepository materiaRepository;

    @Override
    public List<Materia> findAll() {
        log.info("Recupero lista di tutte le materie");
        return materiaRepository.findAll();
    }

    @Override
    public Optional<Materia> findById(Long id) {
        log.info("Recupero materia con ID {}", id);
        return materiaRepository.findById(id);
    }

    @Override
    public Materia save(Materia materia) {
        log.info("Salvataggio nuova materia: {}", materia.getNome());
        return materiaRepository.save(materia);
    }

    @Override
    public Materia update(Long id, Materia materia) {
        log.info("Aggiornamento materia con ID {}", id);
        Optional<Materia> existing = materiaRepository.findById(id);
        if (existing.isPresent()) {
            Materia toUpdate = existing.get();
            toUpdate.setNome(materia.getNome());
            toUpdate.setDescrizione(materia.getDescrizione());
            toUpdate.setOre(materia.getOre());
            return materiaRepository.save(toUpdate);
        } else {
            throw new RuntimeException("Materia non trovata con ID:" + id);
        }
    }

    @Override
    public void deleteById(Long id) {
        log.info("Eliminazione materia con ID {}", id);
        materiaRepository.deleteById(id);
    }
}
