package de.raidcomp.data.repository;

import de.raidcomp.data.entity.DiscordMessageEntity;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

@JdbcRepository(dialect = Dialect.MYSQL)
public interface DiscordMessageRepository extends CrudRepository<DiscordMessageEntity, String> {

}
