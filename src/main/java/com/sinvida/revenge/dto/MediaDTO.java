package com.sinvida.revenge.dto;

import com.sinvida.revenge.enums.MediaType;
import jakarta.validation.constraints.*;

public record MediaDTO(
        @NotNull
        Long revengePlanId,

        @NotNull
        MediaType type,

        @NotBlank
        String url,

        String caption
) {}