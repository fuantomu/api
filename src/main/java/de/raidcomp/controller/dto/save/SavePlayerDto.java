package de.raidcomp.controller.dto.save;

import de.raidcomp.controller.dto.PlayerDto;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotNull;

@Serdeable
@Introspected
public record SavePlayerDto(@NotNull PlayerDto player) {
}
