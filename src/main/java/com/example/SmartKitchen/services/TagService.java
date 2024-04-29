package com.example.SmartKitchen.services;

import com.example.SmartKitchen.dto.TagDTO;
import com.example.SmartKitchen.models.Tag;
import com.example.SmartKitchen.repositories.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    public Tag create(TagDTO dto) {
        return tagRepository.save(Tag.builder()
                        .name(dto.getName())
                .build());
    }

    public void addTags(List<String> stringTags) {
        stringTags.forEach(element ->  {
            if (!tagRepository.existsByName(element)) {
                tagRepository.save(Tag.builder()
                                .name(element)
                        .build());
            }
        });
    }

    public List<Tag> getAll() {
        return tagRepository.findAll();
    }

    public Tag updateById(Long id, TagDTO dto) {
        if (tagRepository.existsById(id)) {
            Tag tag = tagRepository.findById(id).orElseThrow();
            tag.setName(dto.getName());
            return tagRepository.save(tag);
        } else
            return null;
    }

    public boolean deleteById(Long id) {
        if (!tagRepository.existsById(id)) {
            return false;
        }
        tagRepository.deleteById(id);
        return true;
    }
}
