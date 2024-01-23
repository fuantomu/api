package de.raidcomp.data.repository;

import de.raidcomp.data.entity.BuildEntity;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

@JdbcRepository(dialect = Dialect.MYSQL)
public interface BuildRepository extends CrudRepository<BuildEntity, String> {

}
