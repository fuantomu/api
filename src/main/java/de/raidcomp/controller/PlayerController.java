package de.raidcomp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import jakarta.validation.Valid;
import de.raidcomp.controller.dto.PlayerDto;
import de.raidcomp.controller.dto.save.SavePlayerDto;
import de.raidcomp.controller.dto.save.SavePlayersDto;
import de.raidcomp.data.entity.PlayerEntity;
import de.raidcomp.data.repository.PlayerRepository;

@Controller("/player/")
public class PlayerController {

  protected final PlayerRepository playerRepository;

  public PlayerController(PlayerRepository playerRepository) {
    this.playerRepository = playerRepository;
  }

  @Get("/")
  public List<PlayerEntity> getPlayers() {
    return playerRepository.findAll();
  }

  @Get("/{playerId}")
  public Optional<PlayerEntity> getPlayer(String playerId) {
    return playerRepository.findById(playerId);
  }

  @Get("/delete/{playerId}")
  public void deletePlayer(String playerId) {
    System.out.println(playerId);
    playerRepository.deleteById(playerId);
  }

  @Post("/{playerId}")
  public void savePlayer(String playerId, @Valid @Body SavePlayerDto body) {
    PlayerEntity newPlayer = new PlayerEntity();
    newPlayer.setId((body.player().id() == null) ? playerId : body.player().id());
    newPlayer.setName(body.player().name());
    newPlayer.setMain(body.player().main());
    newPlayer.setGroup_id(body.player().group_id().toString());
    newPlayer.setClass_name(body.player().class_name().toString());
    newPlayer.setRace(body.player().race().toString());
    newPlayer.setSpec(body.player().spec().toString());
    newPlayer.setStatus(body.player().status().toString());
    newPlayer.setAlt(body.player().alt());

    Optional<PlayerEntity> player = playerRepository.findById(playerId);

    if (player.isEmpty()) {
      playerRepository.save(newPlayer);
    } else {
      playerRepository.update(newPlayer);
    }

  }

  @Post("/")
  public void savePlayers(@Valid @Body SavePlayersDto body) {
    List<PlayerEntity> newPlayers = new ArrayList<PlayerEntity>();
    List<PlayerEntity> updatePlayers = new ArrayList<PlayerEntity>();

    for (PlayerDto playerDto : body.players()) {
      PlayerEntity newPlayer = new PlayerEntity();
      newPlayer.setId((playerDto.id() == null) ? UUID.randomUUID().toString() : playerDto.id());
      newPlayer.setName(playerDto.name());
      newPlayer.setMain(playerDto.main());
      newPlayer.setGroup_id(playerDto.group_id().toString());
      newPlayer.setClass_name(playerDto.class_name().toString());
      newPlayer.setRace(playerDto.race().toString());
      newPlayer.setSpec(playerDto.spec().toString());
      newPlayer.setStatus(playerDto.status().toString());
      newPlayer.setAlt(playerDto.alt());

      Optional<PlayerEntity> existingPlayer = playerRepository.findById(newPlayer.getId());
      if (existingPlayer.isEmpty()) {
        newPlayers.add(newPlayer);
      } else {
        updatePlayers.add(newPlayer);
      }

    }

    playerRepository.saveAll(newPlayers);
    playerRepository.updateAll(updatePlayers);
  }
}
