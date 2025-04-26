package com.scuola.controller;

import com.scuola.dto.CorsoDTO;
import com.scuola.dto.CorsoDetailDTO;
import com.scuola.mapper.CorsoMapper;
import com.scuola.model.Corso;
import com.scuola.model.Esame;
import com.scuola.model.Materia;
import com.scuola.service.CorsoService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/corsi")
public class CorsoController {

    @Autowired
    private CorsoService corsoService;

    @GetMapping
    public ResponseEntity<List<CorsoDTO>> getAll() {
        List<CorsoDTO> corsi = corsoService.findAll().stream().map(CorsoMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(corsi);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CorsoDTO> getById(@PathVariable Long id) {
        return corsoService.findById(id)
                .map(CorsoMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("Corso non trovato con ID: " + id));
    }

    @GetMapping("/dettaglio/{id}")
    public ResponseEntity<CorsoDetailDTO> getDettaglioCorso(@PathVariable Long id) {
        return corsoService.findById(id)
                .map(CorsoMapper::toDetailDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("Corso non trovato con ID: " + id));
    }

    @GetMapping("/export/excel")
    public void exportCorsiToExcel(HttpServletResponse response) throws Exception {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=corsi.xlsx");

        List<Corso> corsi = corsoService.findAll();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Corsi");

        CellStyle headerStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        headerStyle.setFont(font);
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);


        String[] headers = {"Nome Corso", "Descrizione Corso", "Nome Materia", "Descrizione Materia", "Nome Esame"};
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }


        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);

        int rowNum = 1;
        for (Corso corso : corsi) {
            if (corso.getMaterie() != null && !corso.getMaterie().isEmpty()) {
                for (Materia materia : corso.getMaterie()) {
                    if (materia.getEsami() != null && !materia.getEsami().isEmpty()) {
                        for (Esame esame : materia.getEsami()) {
                            Row row = sheet.createRow(rowNum++);
                            writeRow(row, corso, materia, esame, cellStyle);
                        }
                    } else {
                        Row row = sheet.createRow(rowNum++);
                        writeRow(row, corso, materia, null, cellStyle);
                    }
                }
            } else {
                Row row = sheet.createRow(rowNum++);
                writeRow(row, corso, null, null, cellStyle);
            }
        }


        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        workbook.write(response.getOutputStream());
        workbook.close();
    }

    private void writeRow(Row row, Corso corso, Materia materia, Esame esame, CellStyle style) {
        int col = 0;

        Cell cell = row.createCell(col++);
        cell.setCellValue(corso.getNome());
        cell.setCellStyle(style);

        cell = row.createCell(col++);
        cell.setCellValue(corso.getDescrizione());
        cell.setCellStyle(style);

        if (materia != null) {
            cell = row.createCell(col++);
            cell.setCellValue(materia.getNome());
            cell.setCellStyle(style);

            cell = row.createCell(col++);
            cell.setCellValue(materia.getDescrizione());
            cell.setCellStyle(style);
        } else {
            cell = row.createCell(col++);
            cell.setCellValue("");
            cell.setCellStyle(style);

            cell = row.createCell(col++);
            cell.setCellValue("");
            cell.setCellStyle(style);
        }

        if (esame != null) {
            cell = row.createCell(col++);
            cell.setCellValue(esame.getDescrizione());
            cell.setCellStyle(style);
        } else {
            cell = row.createCell(col++);
            cell.setCellValue("");
            cell.setCellStyle(style);

            cell = row.createCell(col++);
            cell.setCellValue("");
            cell.setCellStyle(style);
        }
    }

    @PostMapping
    public ResponseEntity<CorsoDTO> create(@RequestBody CorsoDTO dto) {
        Corso corso = CorsoMapper.toEntity(dto);
        Corso salvato = corsoService.save(corso);
        return ResponseEntity.ok(CorsoMapper.toDTO(salvato));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CorsoDTO> update(@PathVariable Long id, @RequestBody CorsoDTO dto) {
        Corso aggiornato = corsoService.update(id, CorsoMapper.toEntity(dto));
        return ResponseEntity.ok(CorsoMapper.toDTO(aggiornato));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        corsoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
