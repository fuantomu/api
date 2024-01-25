package de.raidcomp.controller.dto.save;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
@Introspected
public record SaveLoginDto(Integer role, String username) {
}
