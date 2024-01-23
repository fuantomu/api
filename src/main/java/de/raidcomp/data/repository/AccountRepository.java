package de.raidcomp.data.repository;

import de.raidcomp.data.entity.AccountEntity;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

@JdbcRepository(dialect = Dialect.MYSQL)
public interface AccountRepository extends CrudRepository<AccountEntity, String> {

}
