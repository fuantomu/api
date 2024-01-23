package de.raidcomp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import de.raidcomp.model.GroupId.GroupIdSerde;
import de.raidcomp.model.InviteStatus.InviteStatusSerde;
import de.raidcomp.model.WarcraftPlayerClass.WarcraftPlayerClassSerde;
import de.raidcomp.model.WarcraftPlayerRace.WarcraftPlayerRaceSerde;
import de.raidcomp.model.WarcraftPlayerSpec.WarcraftPlayerSpecSerde;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.serde.annotation.Serdeable.Serializable;

public record Player(
    String id,
    String name,
    @JsonProperty("class") @Serializable(using = WarcraftPlayerClassSerde.class) WarcraftPlayerClass class_name,
    @Nullable @Serializable(using = WarcraftPlayerSpecSerde.class) WarcraftPlayerSpec spec,
    @Nullable @Serializable(using = WarcraftPlayerRaceSerde.class) WarcraftPlayerRace race,
    @Serializable(using = InviteStatusSerde.class) InviteStatus status,
    @Nullable @Serializable(using = GroupIdSerde.class) GroupId group_id,
    @Nullable String main,
    @Nullable String alt) {
}
