package com.sinvida.revenge.dto;

import com.sinvida.revenge.enums.SuccessLevel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record RevengePlanDTO(
        @NotBlank
        String title,

        @NotBlank
        String description,

        @NotNull
        Long bullyId,

        boolean isExecuted,

        @NotNull
        LocalDate datePlanned,

        SuccessLevel successLevel
) {}