package com.scuola.controller;

import com.scuola.dto.UtenteDTO;
import com.scuola.dto.UtenteDetailDTO;
import com.scuola.mapper.UtenteMapper;
import com.scuola.model.Utente;
import com.scuola.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utenti")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    @GetMapping
    public ResponseEntity<List<UtenteDTO>> getAll() {
        List<UtenteDTO> lista = utenteService.findAll().stream().map(UtenteMapper::toDTO).toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UtenteDTO> getById(@PathVariable Long id) {
        return utenteService.findById(id)
                .map(UtenteMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("Utente non trovato con ID: " + id));
    }

    @GetMapping("/dettaglio/{id}")
    public ResponseEntity<UtenteDetailDTO> getDettaglioUtente(@PathVariable Long id) {
        return utenteService.findById(id)
                .map(UtenteMapper::toDetailDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("Utente non trovato con ID: " + id));
    }

    @PostMapping
    public ResponseEntity<UtenteDTO> create(@RequestBody UtenteDTO dto) {
        Utente utente = UtenteMapper.toEntity(dto);
        Utente salvato = utenteService.save(utente);
        return ResponseEntity.ok(UtenteMapper.toDTO(salvato));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UtenteDTO> update(@PathVariable Long id, @RequestBody UtenteDTO dto) {
        Utente aggiornato = utenteService.update(id, UtenteMapper.toEntity(dto));
        return ResponseEntity.ok(UtenteMapper.toDTO(aggiornato));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        utenteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
