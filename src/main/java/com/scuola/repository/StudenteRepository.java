package com.scuola.repository;

import com.scuola.model.Studente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudenteRepository extends JpaRepository<Studente, Long> {
}
