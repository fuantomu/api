package de.raidcomp.controller.dto.save;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;

@Serdeable
@Introspected
public record SaveDiscordMessageDto(@NotBlank String messageId, String note) {
}
