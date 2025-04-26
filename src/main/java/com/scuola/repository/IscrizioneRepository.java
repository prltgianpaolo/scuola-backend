package com.scuola.repository;

import com.scuola.model.Iscrizione;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IscrizioneRepository extends JpaRepository<Iscrizione, Long> {
}
