package com.scuola.controller;

import com.scuola.dto.RuoloDTO;
import com.scuola.dto.RuoloDetailDTO;
import com.scuola.mapper.RuoloMapper;
import com.scuola.model.Ruolo;
import com.scuola.service.RuoloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ruoli")
public class RuoloController {

    @Autowired
    private RuoloService ruoloService;

    @GetMapping
    public ResponseEntity<List<RuoloDTO>> getAll() {
        List<RuoloDTO> lista = ruoloService.findAll().stream().map(RuoloMapper::toDTO).toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RuoloDTO> getById(@PathVariable Long id) {
        return ruoloService.findById(id)
                .map(RuoloMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("Ruolo non trovato con ID: " + id));
    }

    @GetMapping("/dettaglio/{id}")
    public ResponseEntity<RuoloDetailDTO> getDettaglioRuolo(@PathVariable Long id) {
        return ruoloService.findById(id)
                .map(RuoloMapper::toDetailDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("Ruolo non trovato con ID: " + id));
    }

    @PostMapping
    public ResponseEntity<RuoloDTO> create(@RequestBody RuoloDTO dto) {
        Ruolo ruolo = RuoloMapper.toEntity(dto);
        Ruolo salvato = ruoloService.save(ruolo);
        return ResponseEntity.ok(RuoloMapper.toDTO(salvato));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RuoloDTO> update(@PathVariable Long id, @RequestBody RuoloDTO dto) {
        Ruolo aggiornato = ruoloService.update(id, RuoloMapper.toEntity(dto));
        return ResponseEntity.ok(RuoloMapper.toDTO(aggiornato));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ruoloService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
