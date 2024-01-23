package de.raidcomp.controller.dto.save;

import de.raidcomp.controller.dto.AbsenceDto;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotNull;

@Serdeable
@Introspected
public record SaveAbsenceDto(@NotNull AbsenceDto absence) {
}
