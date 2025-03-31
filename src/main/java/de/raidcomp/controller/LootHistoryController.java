package de.raidcomp.controller;

import java.util.Comparator;
import java.util.List;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import de.raidcomp.data.entity.LootHistoryEntity;
import de.raidcomp.data.repository.LootHistoryRepository;

@Controller("/loot")
public class LootHistoryController {

  protected final LootHistoryRepository lootHistoryRepository;

  public LootHistoryController(LootHistoryRepository lootHistoryRepository) {
    this.lootHistoryRepository = lootHistoryRepository;
  }

  @Get("/")
  public List<LootHistoryEntity> listAllLootHistories() {
    return lootHistoryRepository.findAll();
  }

  @Get("/{playerName}")
  public List<LootHistoryEntity> listAllLootByPlayer(String playerName) {
    return lootHistoryRepository.findAllByPlayer(playerName).stream()
        .sorted(Comparator.comparing(LootHistoryEntity::getDi, (d1, d2) -> {
          return Integer.valueOf(d2).compareTo(Integer.valueOf(d1));
        })).toList();
  }

}
