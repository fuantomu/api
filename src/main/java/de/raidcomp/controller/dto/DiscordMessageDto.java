package de.raidcomp.controller.dto;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotNull;

@Serdeable
public record DiscordMessageDto(
        @NotNull String messageId,
        @NotNull String buildId,
        String note) {
}
