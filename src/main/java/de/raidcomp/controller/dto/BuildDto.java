package de.raidcomp.controller.dto;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotNull;

@Serdeable
public record BuildDto(
        @NotNull String id,
        @NotNull long date,
        @NotNull String name,
        @NotNull String players,
        String build_id,
        String instance,
        String raid_id) {
}
