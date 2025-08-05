package com.sinvida.revenge.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record ReflectionTrackerDTO(
        @NotNull
        LocalDate date,

        @Min(1)
        @Max(10)
        int moodLevel,

        String note
) {}