package com.scuola.controller;

import com.scuola.dto.LezioneDTO;
import com.scuola.dto.LezioneDetailDTO;
import com.scuola.mapper.LezioneMapper;
import com.scuola.model.Lezione;
import com.scuola.service.LezioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lezioni")
public class LezioneController {

    @Autowired
    private LezioneService lezioneService;

    @GetMapping
    public ResponseEntity<List<LezioneDTO>> getAll() {
        List<LezioneDTO> lista = lezioneService.findAll().stream().map(LezioneMapper::toDTO).toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LezioneDTO> getById(@PathVariable Long id) {
        return lezioneService.findById(id)
                .map(LezioneMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("Lezione non trovata con ID: " + id));
    }

    @GetMapping("/dettaglio/{id}")
    public ResponseEntity<LezioneDetailDTO> getDettaglioLezione(@PathVariable Long id) {
        return lezioneService.findById(id)
                .map(LezioneMapper::toDetailDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("Lezione non trovata con ID: " + id));
    }

    @PostMapping
    public ResponseEntity<LezioneDTO> create(@RequestBody LezioneDTO dto) {
        Lezione lezione = LezioneMapper.toEntity(dto);
        Lezione salvata = lezioneService.save(lezione);
        return ResponseEntity.ok(LezioneMapper.toDTO(salvata));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LezioneDTO> update(@PathVariable Long id, @RequestBody LezioneDTO dto) {
        Lezione aggiornata = lezioneService.update(id, LezioneMapper.toEntity(dto));
        return ResponseEntity.ok(LezioneMapper.toDTO(aggiornata));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        lezioneService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

