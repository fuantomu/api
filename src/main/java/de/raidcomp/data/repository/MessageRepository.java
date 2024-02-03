package de.raidcomp.data.repository;

import java.util.List;

import de.raidcomp.data.entity.MessageEntity;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

@JdbcRepository(dialect = Dialect.MYSQL)
public interface MessageRepository extends CrudRepository<MessageEntity, String> {
    List<MessageEntity> listOrderByDateDesc();
}
