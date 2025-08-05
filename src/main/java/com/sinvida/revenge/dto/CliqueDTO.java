package com.sinvida.revenge.dto;

import jakarta.validation.constraints.NotBlank;

public record CliqueDTO(
        @NotBlank
        String name,

        @NotBlank
        String description
) {
}
