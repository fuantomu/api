package de.raidcomp.controller.dto;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

@Serdeable
public record AbsenceDto(
    @NotNull String id,
    @NotNull String player_id,
    @NotNull Long start_date,
    @NotNull Long end_date,
    @Nullable String reason) {
}
