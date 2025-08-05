package com.sinvida.revenge.dto;

import com.sinvida.revenge.enums.HighSchoolRole;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BullyDTO(
        @NotBlank
        String name,

        @NotBlank
        String nickname,

        @NotNull
        HighSchoolRole highSchoolRole,

        @NotNull
        Long cliqueId,

        @NotBlank
        String bullyingReason,

        @Min(1)
        @Max(10)
        int levelOfAnnoyance
) {
}
