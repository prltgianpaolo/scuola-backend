package com.scuola.repository;

import com.scuola.model.Corso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorsoRepository extends JpaRepository<Corso, Long> {
}
