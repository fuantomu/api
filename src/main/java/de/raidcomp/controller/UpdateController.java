package de.raidcomp.controller;

import java.util.List;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import de.raidcomp.controller.dto.UpdateDto;
import de.raidcomp.data.entity.AbsenceEntity;
import de.raidcomp.data.entity.BuildEntity;
import de.raidcomp.data.entity.PlayerEntity;
import de.raidcomp.data.repository.AbsenceRepository;
import de.raidcomp.data.repository.BuildRepository;
import de.raidcomp.data.repository.PlayerRepository;

@Controller("/update/")
public class UpdateController {

  protected final AbsenceRepository absenceRepository;
  protected final BuildRepository buildRepository;
  protected final PlayerRepository playerRepository;

  public UpdateController(AbsenceRepository absenceRepository, BuildRepository buildRepository,
      PlayerRepository playerRepository) {
    this.absenceRepository = absenceRepository;
    this.buildRepository = buildRepository;
    this.playerRepository = playerRepository;
  }

  @Get("/")
  public UpdateDto getUpdates() {
    BuildController buildController = new BuildController(buildRepository);
    PlayerController playerController = new PlayerController(playerRepository);
    AbsenceController absenceController = new AbsenceController(absenceRepository);
    List<BuildEntity> builds = buildController.listAllBuilds();
    List<PlayerEntity> players = playerController.getPlayers();
    for (BuildEntity build : builds) {
      build.setPlayers("");
    }
    ;
    List<AbsenceEntity> absences = absenceController.getAbsences();
    return new UpdateDto(builds, players, absences);
  }

}
