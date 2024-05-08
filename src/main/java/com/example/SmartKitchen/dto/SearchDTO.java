package com.example.SmartKitchen.dto;

import lombok.Data;

import java.util.List;

@Data
public class SearchDTO {
    String text;
    List<String> tags;
}
