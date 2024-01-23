package de.raidcomp.mapper;

import org.mapstruct.Mapper;

import de.raidcomp.config.MapstructConfig;
import de.raidcomp.controller.dto.PlayerDto;
import de.raidcomp.data.entity.PlayerEntity;
import de.raidcomp.model.Player;

@Mapper(config = MapstructConfig.class)
public interface PlayerMapper {
  Player toDomain(PlayerDto dto);

  PlayerEntity toModel(Player player);

  Player toDomain(PlayerEntity player);

  PlayerDto toDto(Player dto);
}
