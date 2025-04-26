package com.scuola.repository;

import com.scuola.model.Lezione;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LezioneRepository extends JpaRepository<Lezione, Long> {
}
