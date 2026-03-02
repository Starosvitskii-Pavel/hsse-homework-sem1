package com.mipt.pavelstarosvitskiy.dto;

public record TaskDto(
        String id,
        String title,
        String description,
        boolean completed
) {
}
