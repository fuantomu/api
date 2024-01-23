package de.raidcomp.data.entity;

import java.util.UUID;

import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

@Getter
@Setter
@MappedEntity("Absence")
@Table(name = "Absence")
@Serdeable
@Entity
public class AbsenceEntity {
  @Id
  private String id = UUID.randomUUID().toString();

  private String player_id;

  private long start_date;

  private long end_date;

  private String reason;
}
