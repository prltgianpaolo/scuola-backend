package com.scuola.controller;

import com.scuola.dto.EsameDTO;
import com.scuola.dto.EsameDetailDTO;
import com.scuola.mapper.EsameMapper;
import com.scuola.model.Esame;
import com.scuola.service.EsameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/esami")
public class EsameController {

    @Autowired
    private EsameService esameService;

    @GetMapping
    public ResponseEntity<List<EsameDTO>> getAll() {
        List<EsameDTO> lista = esameService.findAll().stream().map(EsameMapper::toDTO).toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EsameDTO> getById(@PathVariable Long id) {
        return esameService.findById(id)
                .map(EsameMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("Esame non trovato con ID: " + id));
    }

    @GetMapping("/dettaglio/{id}")
    public ResponseEntity<EsameDetailDTO> getDettaglioEsame(@PathVariable Long id) {
        return esameService.findById(id)
                .map(EsameMapper::toDetailDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("Esame non trovato con ID: " + id));
    }

    @PostMapping
    public ResponseEntity<EsameDTO> create(@RequestBody EsameDTO dto) {
        Esame esame = EsameMapper.toEntity(dto);
        Esame salvato = esameService.save(esame);
        return ResponseEntity.ok(EsameMapper.toDTO(salvato));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EsameDTO> update(@PathVariable Long id, @RequestBody EsameDTO dto) {
        Esame aggiornato = esameService.update(id, EsameMapper.toEntity(dto));
        return ResponseEntity.ok(EsameMapper.toDTO(aggiornato));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        esameService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
