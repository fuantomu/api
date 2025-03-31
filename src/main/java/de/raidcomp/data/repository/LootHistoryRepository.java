package de.raidcomp.data.repository;

import java.util.List;

import de.raidcomp.data.entity.LootHistoryEntity;
import io.micronaut.context.annotation.Parameter;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

@JdbcRepository(dialect = Dialect.MYSQL)
public interface LootHistoryRepository extends CrudRepository<LootHistoryEntity, String> {

    @Query("select * from lootcouncil o where o.player like CONCAT(:player,'-%')")
    List<LootHistoryEntity> findAllByPlayer(@Parameter("player") String player);

}
