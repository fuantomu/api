package de.raidcomp.controller.dto;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotNull;

@Serdeable
public record UserDto(
        @NotNull String host,
        @NotNull String username) {
}
