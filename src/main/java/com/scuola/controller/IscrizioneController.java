package com.scuola.controller;

import com.scuola.dto.IscrizioneDTO;
import com.scuola.dto.IscrizioneDetailDTO;
import com.scuola.mapper.IscrizioneMapper;
import com.scuola.model.Iscrizione;
import com.scuola.service.IscrizioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/iscrizioni")
public class IscrizioneController {

    @Autowired
    private IscrizioneService iscrizioneService;

    @GetMapping
    public ResponseEntity<List<IscrizioneDTO>> getAll() {
        List<IscrizioneDTO> lista = iscrizioneService.findAll().stream().map(IscrizioneMapper::toDTO).toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IscrizioneDTO> getById(@PathVariable Long id) {
        return iscrizioneService.findById(id)
                .map(IscrizioneMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("Iscrizione non trovata con ID: " + id));
    }

    @GetMapping("/dettaglio/{id}")
    public ResponseEntity<IscrizioneDetailDTO> getDettaglioIscrizione(@PathVariable Long id) {
        return iscrizioneService.findById(id)
                .map(IscrizioneMapper::toDetailDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("Iscrizione non trovata con ID: " + id));
    }

    @PostMapping
    public ResponseEntity<IscrizioneDTO> create(@RequestBody IscrizioneDTO dto) {
        Iscrizione iscrizione = IscrizioneMapper.toEntity(dto);
        Iscrizione salvata = iscrizioneService.save(iscrizione);
        return ResponseEntity.ok(IscrizioneMapper.toDTO(salvata));
    }

    @PutMapping("/{id}")
    public ResponseEntity<IscrizioneDTO> update(@PathVariable Long id, @RequestBody IscrizioneDTO dto) {
        Iscrizione aggiornata = iscrizioneService.update(id, IscrizioneMapper.toEntity(dto));
        return ResponseEntity.ok(IscrizioneMapper.toDTO(aggiornata));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        iscrizioneService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
