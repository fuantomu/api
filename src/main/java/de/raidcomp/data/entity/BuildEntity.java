package de.raidcomp.data.entity;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedEntity("Build")
@Table(name = "Build")
@Serdeable
@Entity
public class BuildEntity {
  @Id
  private String id;

  @NonNull
  private String name;

  private Long date;

  @JdbcTypeCode(SqlTypes.LONGVARCHAR)
  private String players;

  private String raid_id;

  private String instance;
}
