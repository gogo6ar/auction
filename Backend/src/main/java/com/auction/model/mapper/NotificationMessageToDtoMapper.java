package com.auction.model.mapper;

import com.auction.model.NotificationMessageUser;
import com.auction.web.dto.NotificationMessageDto;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
class NotificationMessageToDtoMapper implements Mapper<NotificationMessageUser, NotificationMessageDto> {

  @Override
  public NotificationMessageDto map(NotificationMessageUser entity) {
    return NotificationMessageDto.builder()
            .messageId(entity.getNotificationMessage().getId())
            .message(entity.getNotificationMessage().getMessage())
            .type(entity.getNotificationMessage().getType())
            .genDate(entity.getNotificationMessage().getGenDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
            .seen(entity.getSeen())
            .image(entity.getNotificationMessage().getImage())
            .build();
  }
}
