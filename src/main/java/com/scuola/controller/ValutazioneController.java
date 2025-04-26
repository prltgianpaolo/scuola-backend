package com.scuola.controller;

import com.scuola.dto.ValutazioneDTO;
import com.scuola.dto.ValutazioneDetailDTO;
import com.scuola.mapper.ValutazioneMapper;
import com.scuola.model.Valutazione;
import com.scuola.service.ValutazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/valutazioni")
public class ValutazioneController {

    @Autowired
    private ValutazioneService valutazioneService;

    @GetMapping
    public ResponseEntity<List<ValutazioneDTO>> getAll() {
        List<ValutazioneDTO> lista = valutazioneService.findAll().stream().map(ValutazioneMapper::toDTO).toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ValutazioneDTO> getById(@PathVariable Long id) {
        return valutazioneService.findById(id)
                .map(ValutazioneMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("Valutazione non trovata con ID: " + id));
    }

    @GetMapping("/dettaglio/{id}")
    public ResponseEntity<ValutazioneDetailDTO> getDettaglioValutazione(@PathVariable Long id) {
        return valutazioneService.findById(id)
                .map(ValutazioneMapper::toDetailDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("Valutazione non trovata con ID: " + id));
    }

    @PostMapping
    public ResponseEntity<ValutazioneDTO> create(@RequestBody ValutazioneDTO dto) {
        Valutazione valutazione = ValutazioneMapper.toEntity(dto);
        Valutazione salvata = valutazioneService.save(valutazione);
        return ResponseEntity.ok(ValutazioneMapper.toDTO(salvata));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ValutazioneDTO> update(@PathVariable Long id, @RequestBody ValutazioneDTO dto) {
        Valutazione aggiornata = valutazioneService.update(id, ValutazioneMapper.toEntity(dto));
        return ResponseEntity.ok(ValutazioneMapper.toDTO(aggiornata));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        valutazioneService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
