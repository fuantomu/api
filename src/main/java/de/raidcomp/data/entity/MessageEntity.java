package de.raidcomp.data.entity;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

@Getter
@Setter
@MappedEntity("message")
@Table(name = "message")
@Entity
@Serdeable
public class MessageEntity {
  @Id
  private String id;

  private String message_type;

  @JdbcTypeCode(SqlTypes.LONGVARCHAR)
  private String data;

  private String account_name;

  private Long date;
}
