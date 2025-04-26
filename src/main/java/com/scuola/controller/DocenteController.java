package com.scuola.controller;

import com.scuola.dto.DocenteDTO;
import com.scuola.dto.DocenteDetailDTO;
import com.scuola.mapper.DocenteMapper;
import com.scuola.model.Docente;
import com.scuola.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/docenti")
public class DocenteController {

    @Autowired
    private DocenteService docenteService;

    @GetMapping
    public ResponseEntity<List<DocenteDTO>> getAll() {
        List<DocenteDTO> lista = docenteService.findAll().stream().map(DocenteMapper::toDTO).toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocenteDTO> getById(@PathVariable Long id) {
        return docenteService.findById(id)
                .map(DocenteMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("Docente non trovato con ID: " + id));
    }

    @GetMapping("/dettaglio/{id}")
    public ResponseEntity<DocenteDetailDTO> getDettaglioDocente(@PathVariable Long id) {
        return docenteService.findById(id)
                .map(DocenteMapper::toDetailDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("Docente non trovato con ID: " + id));
    }


    @PostMapping
    public ResponseEntity<DocenteDTO> create(@RequestBody DocenteDTO dto) {
        Docente docente = DocenteMapper.toEntity(dto);
        Docente salvato = docenteService.save(docente);
        return ResponseEntity.ok(DocenteMapper.toDTO(salvato));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocenteDTO> update(@PathVariable Long id, @RequestBody DocenteDTO dto) {
        Docente aggiornato = docenteService.update(id, DocenteMapper.toEntity(dto));
        return ResponseEntity.ok(DocenteMapper.toDTO(aggiornato));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        docenteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
