package com.example.SmartKitchen.services;

import com.example.SmartKitchen.dto.MeasureDTO;
import com.example.SmartKitchen.models.Measure;
import com.example.SmartKitchen.repositories.MeasureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MeasureService {
    private final MeasureRepository measureRepository;

    public Measure create(MeasureDTO dto) {
        return measureRepository.save(Measure.builder()
                    .name(dto.getName())
                    .build());
    }

    public List<Measure> getAll() {
        return measureRepository.findAll();
    }

    public Measure updateById(Long id, MeasureDTO dto) {
        if(measureRepository.existsById(id)) {
            return measureRepository.save(Measure.builder()
                    .id(id)
                    .name(dto.getName())
                    .build());
        } else {
            return null;
        }
    }

    public boolean deleteById(Long id) {
        if (!measureRepository.existsById(id)) {
            return false;
        }
        measureRepository.deleteById(id);
        return true;
    }
}
