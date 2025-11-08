package com.geogieoddae.mainservice.notification.command.event;


public record AlarmCreatedEvent(Long userId, Long notificationTypeCode) {}