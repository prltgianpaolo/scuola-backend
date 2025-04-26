package com.scuola.controller;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.scuola.dto.StudenteDTO;
import com.scuola.dto.StudenteDetailDTO;
import com.scuola.mapper.StudenteMapper;
import com.scuola.model.Studente;
import com.scuola.model.Valutazione;
import com.scuola.service.StudenteService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/studenti")
public class StudenteController {

    @Autowired
    private StudenteService studenteService;

    @GetMapping
    public ResponseEntity<List<StudenteDTO>> getAll() {
        List<StudenteDTO> lista = studenteService.findAll().stream().map(StudenteMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudenteDTO> getById(@PathVariable Long id) {
        return studenteService.findById(id)
                .map(StudenteMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("Studente non trovato con ID: " + id));
    }

    @GetMapping("/dettaglio/{id}")
    public ResponseEntity<StudenteDetailDTO> getDettaglioStudente(@PathVariable Long id) {
        return studenteService.findById(id)
                .map(StudenteMapper::toDetailDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("Studente non trovato con ID: " + id));
    }

    @GetMapping("/export/csv")
    public void exportStudentiToCsv(HttpServletResponse response) throws Exception {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"studenti.csv\"");

        List<Studente> studenti = studenteService.findAll();

        var writer = response.getWriter();
        writer.println("ID,Nome,Cognome,Email,Telefono");

        for (Studente studente : studenti) {
            writer.println(String.format(
                    "%d,%s,%s,%s,%s",
                    studente.getId(),
                    studente.getNome(),
                    studente.getCognome(),
                    studente.getEmail(),
                    studente.getTelefono()
            ));
        }

        writer.flush();
    }

    @GetMapping("/export/pdf/{id}")
    public void exportProfiloStudenteToPdf(@PathVariable Long id, HttpServletResponse response) throws Exception {
        Studente studente = studenteService.findById(id)
                .orElseThrow(() -> new RuntimeException("Studente non trovato con ID: " + id));

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=profilo_studente_" + id + ".pdf");

        com.lowagie.text.Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        // logo
        Font logoFont = new Font(Font.HELVETICA, 28, Font.BOLD, Color.BLUE);
        Paragraph logo = new Paragraph("INFOBASIC", logoFont);
        logo.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(logo);

        document.add(new Paragraph(" "));

        // Titolo Profilo
        Font titleFont = new Font(Font.HELVETICA, 20, Font.BOLD);
        Paragraph title = new Paragraph("Profilo Studente", titleFont);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(title);

        document.add(new Paragraph(" "));

        // Dati Studente
        Font dataFont = new Font(Font.HELVETICA, 12, Font.NORMAL);
        document.add(new Paragraph("Nome: " + studente.getNome(), dataFont));
        document.add(new Paragraph("Cognome: " + studente.getCognome(), dataFont));
        document.add(new Paragraph("Email: " + studente.getEmail(), dataFont));
        document.add(new Paragraph("Telefono: " + studente.getTelefono(), dataFont));
        document.add(new Paragraph("Data di nascita: " + studente.getDatanascita(), dataFont));

        document.add(new Paragraph(" "));

        // Tabella valutazioni
        Paragraph subTitle = new Paragraph("Valutazioni", titleFont);
        subTitle.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(subTitle);

        document.add(new Paragraph(" "));

        if (studente.getValutazioni() != null && !studente.getValutazioni().isEmpty()) {
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setWidths(new int[]{3, 1}); // Esame più largo

            PdfPCell cell;

            cell = new PdfPCell(new Phrase("Esame"));
            cell.setBackgroundColor(Color.LIGHT_GRAY);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Voto"));
            cell.setBackgroundColor(Color.LIGHT_GRAY);
            table.addCell(cell);

            for (Valutazione valutazione : studente.getValutazioni()) {
                table.addCell(valutazione.getEsame().getDescrizione());
                table.addCell(String.valueOf(valutazione.getVoto()));
            }

            document.add(table);
        } else {
            document.add(new Paragraph("- Nessuna valutazione disponibile", dataFont));
        }

        document.close();
    }

    @PostMapping
    public ResponseEntity<StudenteDTO> create(@RequestBody StudenteDTO dto) {
        Studente nuovo = StudenteMapper.toEntity(dto);
        Studente salvato = studenteService.save(nuovo);
        return ResponseEntity.ok(StudenteMapper.toDTO(salvato));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudenteDTO> update(@PathVariable Long id, @RequestBody StudenteDTO dto) {
        Studente aggiornato = studenteService.update(id, StudenteMapper.toEntity(dto));
        return ResponseEntity.ok(StudenteMapper.toDTO(aggiornato));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        studenteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
