package de.raidcomp.data.entity;

import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@MappedEntity("lootcouncil")
@Table(name = "lootcouncil")
@Serdeable
public class LootHistoryEntity {
  @Id
  @NotNull
  private String di;

  @NotNull
  private String player;

  @NotNull
  private String date;

  @NotNull
  private String time;

  @NotNull
  private String id;

  @NotNull
  private String item;

  @NotNull
  private String itemid;

  private String itemstring;

  private String response;

  private String votes;

  private String class_;

  private String instance;

  private String boss;

  private String difficultyid;

  private String mapid;

  private String groupsize;

  private String gear1;

  private String gear2;

  @NotNull
  private String responseid;

  private String isawardreason;

  private String subtype;

  private String equiploc;

  private String note;

  @NotNull
  private String owner;
}
