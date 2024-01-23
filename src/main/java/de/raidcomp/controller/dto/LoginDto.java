package de.raidcomp.controller.dto;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotNull;

@Serdeable
public record LoginDto(
    @NotNull Long created_date,
    @NotNull Integer role) {
}
