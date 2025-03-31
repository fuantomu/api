package de.raidcomp.controller.dto;

import de.raidcomp.model.GroupId;
import de.raidcomp.model.InviteStatus;
import de.raidcomp.model.WarcraftPlayerClass;
import de.raidcomp.model.WarcraftPlayerRace;
import de.raidcomp.model.WarcraftPlayerSpec;
import de.raidcomp.model.GroupId.GroupIdSerde;
import de.raidcomp.model.InviteStatus.InviteStatusSerde;
import de.raidcomp.model.WarcraftPlayerClass.WarcraftPlayerClassSerde;
import de.raidcomp.model.WarcraftPlayerRace.WarcraftPlayerRaceSerde;
import de.raidcomp.model.WarcraftPlayerSpec.WarcraftPlayerSpecSerde;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.serde.annotation.Serdeable;
import io.micronaut.serde.annotation.Serdeable.Deserializable;
import jakarta.validation.constraints.NotNull;

@Serdeable
public record PlayerDto(
                @NotNull String id,
                @NotNull String name,
                @NotNull @Deserializable(using = WarcraftPlayerClassSerde.class) WarcraftPlayerClass class_name,
                @Nullable @Deserializable(using = WarcraftPlayerSpecSerde.class) WarcraftPlayerSpec spec,
                @Nullable @Deserializable(using = WarcraftPlayerRaceSerde.class) WarcraftPlayerRace race,
                @NotNull @Deserializable(using = InviteStatusSerde.class) InviteStatus status,
                @Nullable @Deserializable(using = GroupIdSerde.class) GroupId group_id,
                @Nullable String main,
                @Nullable String alt,
                @Nullable String role,
                @Nullable String swap) {
}
