package com.scuola.config;

import com.scuola.model.*;
import com.scuola.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(
            StudenteRepository studenteRepository,
            DocenteRepository docenteRepository,
            MateriaRepository materiaRepository,
            CorsoRepository corsoRepository,
            EsameRepository esameRepository,
            AulaRepository aulaRepository,
            LezioneRepository lezioneRepository,
            ValutazioneRepository valutazioneRepository,
            IscrizioneRepository iscrizioneRepository,
            RegistroPresenzeRepository registroPresenzeRepository,
            UtenteRepository utenteRepository,
            RuoloRepository ruoloRepository
    ) {
        return args -> {
            // CREA STUDENTI
            Studente mario = new Studente();
            mario.setNome("Mario");
            mario.setCognome("Rossi");
            mario.setEmail("mario.rossi@example.com");
            mario.setDatanascita(LocalDate.of(2000, 1, 1));
            mario.setTelefono("3331234567");
            studenteRepository.save(mario);

            // CREA DOCENTI
            Docente alessandro = new Docente();
            alessandro.setNome("Alessandro");
            alessandro.setCognome("Bianchi");
            alessandro.setEmail("alessandro.bianchi@example.com");
            alessandro.setSpecializzazione("Java Specialist");
            docenteRepository.save(alessandro);

            // CREA MATERIE
            Materia javaBase = new Materia();
            javaBase.setNome("Java Base");
            javaBase.setDescrizione("Fondamenti di programmazione Java");
            javaBase.setOre(40);
            javaBase.setDocente(alessandro);
            materiaRepository.save(javaBase);

            // CREA CORSO
            Corso corsoJava = new Corso();
            corsoJava.setNome("Corso Java Completo");
            corsoJava.setDescrizione("Tutto su Java e Spring Boot");
            corsoJava.setDataInizio(LocalDate.of(2025, 4, 1));
            corsoJava.setDataFine(LocalDate.of(2025, 10, 1));
            corsoJava.setOreTotali(300);
            corsoJava.setMaterie(List.of(javaBase));
            corsoRepository.save(corsoJava);

            // CREA AULA
            Aula aula101 = new Aula();
            aula101.setNome("Aula 101");
            aula101.setCapienza(30);
            aula101.setPiano("Piano 1");
            aulaRepository.save(aula101);

            // CREA LEZIONE
            Lezione lezione1 = new Lezione();
            lezione1.setData(LocalDate.of(2025, 5, 15));
            lezione1.setOrarioInizio(LocalTime.of(9, 0));
            lezione1.setOrarioFine(LocalTime.of(11, 0));
            lezione1.setArgomento("Introduzione a Spring Boot");
            lezione1.setDocente(alessandro);
            lezione1.setMateria(javaBase);
            lezione1.setAula(aula101);
            lezioneRepository.save(lezione1);

            // CREA ESAME
            Esame esame1 = new Esame();
            esame1.setDataEsame(LocalDate.of(2025, 7, 10));
            esame1.setDescrizione("Esame Java Base");
            esame1.setMateria(javaBase);
            esameRepository.save(esame1);

            // CREA ISCRIZIONE
            Iscrizione iscrizione = new Iscrizione();
            iscrizione.setDataIscrizione(LocalDate.of(2025, 3, 1));
            iscrizione.setStudente(mario);
            iscrizione.setCorso(corsoJava);
            iscrizioneRepository.save(iscrizione);

            // CREA VALUTAZIONE
            Valutazione valutazione = new Valutazione();
            valutazione.setVoto(28.0);
            valutazione.setStudente(mario);
            valutazione.setEsame(esame1);
            valutazioneRepository.save(valutazione);

            // CREA REGISTRO PRESENZE
            RegistroPresenze presenze = new RegistroPresenze();
            presenze.setPresente(true);
            presenze.setNote("Presente");
            presenze.setStudente(mario);
            presenze.setLezione(lezione1);
            registroPresenzeRepository.save(presenze);

            // CREA RUOLO
            Ruolo adminRole = new Ruolo();
            adminRole.setNome("ADMIN");
            ruoloRepository.save(adminRole);

            // CREA UTENTE
            Utente adminUser = new Utente();
            adminUser.setUsername("admin");
            adminUser.setPassword("password123");
            adminUser.setRuolo(adminRole);
            utenteRepository.save(adminUser);

            System.out.println("✅ Database inizializzato con dati di esempio!");
        };
    }
}
