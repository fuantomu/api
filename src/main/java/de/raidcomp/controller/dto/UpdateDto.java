package de.raidcomp.controller.dto;

import java.util.List;

import de.raidcomp.data.entity.AbsenceEntity;
import de.raidcomp.data.entity.BuildEntity;
import de.raidcomp.data.entity.PlayerEntity;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotNull;

@Serdeable
public record UpdateDto(
    @NotNull List<BuildEntity> builds,
    @NotNull List<PlayerEntity> players,
    @NotNull List<AbsenceEntity> absences) {
}
