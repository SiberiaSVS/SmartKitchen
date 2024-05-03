package com.example.SmartKitchen.controllers;

import com.example.SmartKitchen.dto.TagDTO;
import com.example.SmartKitchen.models.Tag;
import com.example.SmartKitchen.services.TagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tag")
public class TagController {
    private final TagService tagService;

    @PostMapping
    public ResponseEntity<?> createTag(@Valid @RequestBody TagDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tagService.create(dto));
    }

    @GetMapping
    public ResponseEntity<?> getAllTags() {
        return ResponseEntity.ok(tagService.getAll());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateTag(@PathVariable Long id, @Valid @RequestBody TagDTO dto) {
        Tag tag = tagService.updateById(id, dto);
        return tag == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found") : ResponseEntity.ok(tag);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTag(@PathVariable Long id) {
        if (tagService.deleteById(id))
            return ResponseEntity.ok("Ok");
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
    }
}
