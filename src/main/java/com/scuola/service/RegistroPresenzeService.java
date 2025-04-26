package com.scuola.service;

import com.scuola.model.RegistroPresenze;

import java.util.List;
import java.util.Optional;

public interface RegistroPresenzeService {
    List<RegistroPresenze> findAll();
    Optional<RegistroPresenze> findById(Long id);
    RegistroPresenze save(RegistroPresenze registroPresenze);
    RegistroPresenze update(Long id, RegistroPresenze registroPresenze);
    void deleteById(Long id);
}
