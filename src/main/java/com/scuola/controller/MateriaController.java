package com.scuola.controller;

import com.scuola.dto.MateriaDTO;
import com.scuola.dto.MateriaDetailDTO;
import com.scuola.mapper.MateriaMapper;
import com.scuola.model.Materia;
import com.scuola.service.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materie")
public class MateriaController {

    @Autowired
    private MateriaService materiaService;

    @GetMapping
    public ResponseEntity<List<MateriaDTO>> getAll() {
        List<MateriaDTO> lista = materiaService.findAll().stream().map(MateriaMapper::toDTO).toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MateriaDTO> getById(@PathVariable Long id) {
        return materiaService.findById(id)
                .map(MateriaMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("Materia non trovata con ID: " + id));
    }

    @GetMapping("/dettaglio/{id}")
    public ResponseEntity<MateriaDetailDTO> getDettaglioMateria(@PathVariable Long id) {
        return materiaService.findById(id)
                .map(MateriaMapper::toDetailDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("Materia non trovata con ID: " + id));
    }

    @PostMapping
    public ResponseEntity<MateriaDTO> create(@RequestBody MateriaDTO dto) {
        Materia materia = MateriaMapper.toEntity(dto);
        Materia salvata = materiaService.save(materia);
        return ResponseEntity.ok(MateriaMapper.toDTO(salvata));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MateriaDTO> update(@PathVariable Long id, @RequestBody MateriaDTO dto) {
        Materia aggiornata = materiaService.update(id, MateriaMapper.toEntity(dto));
        return ResponseEntity.ok(MateriaMapper.toDTO(aggiornata));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        materiaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
