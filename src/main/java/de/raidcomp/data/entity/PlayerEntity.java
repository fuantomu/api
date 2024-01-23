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
@MappedEntity("Player")
@Table(name = "Player")
@Entity
@Serdeable
public class PlayerEntity {
  @Id
  private String id;

  private String name;

  private String class_name;

  private String spec;

  private String race;

  private String status;

  private String group_id;

  private String main;

  private String alt;
}
