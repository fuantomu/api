package de.raidcomp.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Comparator;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.QueryValue;
import jakarta.validation.Valid;
import de.raidcomp.controller.dto.save.SaveDiscordMessageDto;
import de.raidcomp.data.entity.DiscordMessageEntity;
import de.raidcomp.data.repository.DiscordMessageRepository;

@Controller("/discord/")
public class DiscordMessageController {

  protected final DiscordMessageRepository discordMessageRepository;

  public DiscordMessageController(DiscordMessageRepository discordMessageRepository) {
    this.discordMessageRepository = discordMessageRepository;
  }

  @Get("/")
  public List<DiscordMessageEntity> listAllDiscordMessages() {
    return discordMessageRepository.findAll();
  }

  @Get("/{buildId}")
  public Optional<DiscordMessageEntity> getDiscord(String buildId) {
    return discordMessageRepository.findById(buildId);
  }

  @Get("/delete/{buildId}")
  public void deleteDiscord(String buildId) {
    discordMessageRepository.deleteById(buildId);
  }

  @Post("/{buildId}")
  public void saveDiscord(String buildId, @Valid @Body SaveDiscordMessageDto body) {
    DiscordMessageEntity newDiscord = new DiscordMessageEntity();
    newDiscord.setMessageId(body.messageId());
    newDiscord.setBuildId(buildId);
    newDiscord.setNote(body.note());

    Optional<DiscordMessageEntity> discord = discordMessageRepository.findById(buildId);

    if (discord.isEmpty()) {
      discordMessageRepository.save(newDiscord);
    } else {
      discordMessageRepository.update(newDiscord);
    }

  }
}
