package com.scuola.controller;

import com.scuola.dto.LoginDTO;
import com.scuola.dto.RegistrazioneDTO;
import com.scuola.dto.UtenteDTO;
import com.scuola.mapper.RegistrazioneMapper;
import com.scuola.mapper.UtenteMapper;
import com.scuola.model.Utente;
import com.scuola.security.JwtUtil;
import com.scuola.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UtenteService utenteService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO request) {
        Utente utente = utenteService.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Utente non trovato"));

        if (!utente.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Password errata");
        }

        String token = JwtUtil.generateToken(utente.getUsername());

        return ResponseEntity.ok(token);
    }

    @PostMapping("/registrazione")
    public ResponseEntity<UtenteDTO> registrazione(@RequestBody RegistrazioneDTO dto) {
        Utente utente = RegistrazioneMapper.toEntity(dto);
        Utente salvato = utenteService.save(utente);
        return ResponseEntity.ok(UtenteMapper.toDTO(salvato));
    }
}
