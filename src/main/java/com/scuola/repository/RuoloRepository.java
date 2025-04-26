package com.scuola.repository;

import com.scuola.model.Ruolo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RuoloRepository extends JpaRepository<Ruolo, Long> {
    Ruolo findByNome(String nome);
}
