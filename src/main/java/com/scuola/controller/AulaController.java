package com.scuola.controller;

import com.scuola.dto.AulaDTO;
import com.scuola.dto.AulaDetailDTO;
import com.scuola.mapper.AulaMapper;
import com.scuola.model.Aula;
import com.scuola.service.AulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aule")
public class AulaController {

    @Autowired
    private AulaService aulaService;

    @GetMapping
    public ResponseEntity<List<AulaDTO>> getAll() {
        List<AulaDTO> lista = aulaService.findAll().stream().map(AulaMapper::toDTO).toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AulaDTO> getById(@PathVariable Long id) {
        return aulaService.findById(id)
                .map(AulaMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("Aula non trovata con ID: " + id));
    }

    @GetMapping("/dettaglio/{id}")
    public ResponseEntity<AulaDetailDTO> getDettaglioAula(@PathVariable Long id) {
        return aulaService.findById(id)
                .map(AulaMapper::toDetailDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("Aula non trovata con ID: " + id));
    }

    @PostMapping
    public ResponseEntity<AulaDTO> create(@RequestBody AulaDTO dto) {
        Aula aula = AulaMapper.toEntity(dto);
        Aula salvata = aulaService.save(aula);
        return ResponseEntity.ok(AulaMapper.toDTO(salvata));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AulaDTO> update(@PathVariable Long id, @RequestBody AulaDTO dto) {
        Aula aggiornata = aulaService.update(id, AulaMapper.toEntity(dto));
        return ResponseEntity.ok(AulaMapper.toDTO(aggiornata));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        aulaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
