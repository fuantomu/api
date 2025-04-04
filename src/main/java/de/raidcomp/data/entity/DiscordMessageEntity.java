package de.raidcomp.data.entity;

import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Getter
@Setter
@MappedEntity("discord")
@Table(name = "discord")
@Serdeable
public class DiscordMessageEntity {
  private String messageId;
  @Id
  private String buildId;

  private String note;
}
