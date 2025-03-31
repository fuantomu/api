package de.raidcomp.controller.dto;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotNull;

@Serdeable
public record LootHistoryDto(
                @NotNull String date,
                @NotNull String time,
                @NotNull String item,
                @NotNull String itemID,
                @NotNull String response,
                String note) {
}