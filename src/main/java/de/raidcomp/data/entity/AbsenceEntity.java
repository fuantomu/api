package de.raidcomp.data.entity;

import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

@Getter
@Setter
@MappedEntity("absence")
@Table(name = "absence")
@Serdeable
@Entity
public class AbsenceEntity {
  @Id
  private String id;

  private String player_id;

  private long start_date;

  private long end_date;

  private String reason;
}
