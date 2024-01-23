package de.raidcomp.controller.dto.save;

import java.util.ArrayList;

import de.raidcomp.controller.dto.PlayerDto;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotNull;

@Serdeable
@Introspected
public record SavePlayersDto(@NotNull ArrayList<PlayerDto> players) {
}
