package de.raidcomp.controller;

import java.util.List;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import de.raidcomp.data.entity.MessageEntity;
import de.raidcomp.data.repository.MessageRepository;

@Controller("/message/")
public class MessageController {

  protected final MessageRepository messageRepository;

  public MessageController(MessageRepository messageRepository) {
    this.messageRepository = messageRepository;
  }

  @Get("/{amount}")
  public List<MessageEntity> getMessages(int amount) {
    List<MessageEntity> messageList = messageRepository.listOrderByDateDesc();
    return messageList.subList(0, Math.min(messageList.size(), amount));
  }

}
