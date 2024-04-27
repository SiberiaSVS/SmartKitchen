package com.example.SmartKitchen.controllers;

import com.example.SmartKitchen.dto.MeasureDTO;
import com.example.SmartKitchen.models.Measure;
import com.example.SmartKitchen.services.MeasureService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/measure")
public class MeasureController {
    private final MeasureService measureService;

    @GetMapping
    public ResponseEntity<?> getAllMeasures() {
        return ResponseEntity.ok(measureService.getAll());
    }

    @PostMapping
    public ResponseEntity<?> createMeasure(@Valid @RequestBody MeasureDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(measureService.create(dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateMeasure(@PathVariable Long id, @Valid @RequestBody MeasureDTO dto) {
        Measure measure = measureService.updateById(id, dto);
        return measure == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found") : ResponseEntity.ok(measure);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMeasure(@PathVariable Long id) {
        if (measureService.deleteById(id))
            return ResponseEntity.ok("Ok");
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
    }


}
