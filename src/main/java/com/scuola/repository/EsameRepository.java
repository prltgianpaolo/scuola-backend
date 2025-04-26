package com.scuola.repository;

import com.scuola.model.Esame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EsameRepository extends JpaRepository<Esame, Long> {
}
