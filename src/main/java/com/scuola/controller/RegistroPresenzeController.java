package com.scuola.controller;

import com.scuola.dto.RegistroPresenzeDTO;
import com.scuola.dto.RegistroPresenzeDetailDTO;
import com.scuola.mapper.RegistroPresenzeMapper;
import com.scuola.model.RegistroPresenze;
import com.scuola.service.RegistroPresenzeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registro_presenze")
public class RegistroPresenzeController {

    @Autowired
    private RegistroPresenzeService registroPresenzeService;

    @GetMapping
    public ResponseEntity<List<RegistroPresenzeDTO>> getAll() {
        List<RegistroPresenzeDTO> lista = registroPresenzeService.findAll().stream().map(RegistroPresenzeMapper::toDTO).toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroPresenzeDTO> getById(@PathVariable Long id) {
        return registroPresenzeService.findById(id)
                .map(RegistroPresenzeMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("Presenza non trovata con ID: " + id));
    }

    @GetMapping("/dettaglio/{id}")
    public ResponseEntity<RegistroPresenzeDetailDTO> getDettaglioPresenza(@PathVariable Long id) {
        return registroPresenzeService.findById(id)
                .map(RegistroPresenzeMapper::toDetailDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("Presenza non trovata con ID: " + id));
    }

    @PostMapping
    public ResponseEntity<RegistroPresenzeDTO> create(@RequestBody RegistroPresenzeDTO dto) {
        RegistroPresenze rp = RegistroPresenzeMapper.toEntity(dto);
        RegistroPresenze salvata = registroPresenzeService.save(rp);
        return ResponseEntity.ok(RegistroPresenzeMapper.toDTO(salvata));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegistroPresenzeDTO> update(@PathVariable Long id, @RequestBody RegistroPresenzeDTO dto) {
        RegistroPresenze aggiornata = registroPresenzeService.update(id, RegistroPresenzeMapper.toEntity(dto));
        return ResponseEntity.ok(RegistroPresenzeMapper.toDTO(aggiornata));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        registroPresenzeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
