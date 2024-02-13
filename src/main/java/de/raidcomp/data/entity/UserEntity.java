package de.raidcomp.data.entity;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Serdeable
public class UserEntity {
  private String host;
  private String username;
  private Long last_message;
}
