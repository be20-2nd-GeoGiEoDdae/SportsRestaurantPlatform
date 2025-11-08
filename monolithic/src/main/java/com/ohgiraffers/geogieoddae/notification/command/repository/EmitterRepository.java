package com.ohgiraffers.geogieoddae.notification.command.repository;

import java.util.Map;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;


public interface EmitterRepository {

  SseEmitter save(String emitterId, SseEmitter sseEmitter);

  void saveEventCache(String emitterId, Object event);

  Map<String, SseEmitter> findAllEmitterStartWithByMemberId(String memberId);

  Map<String, Object> findAllEventCacheStartWithByMemberId(String memberId);

  void deleteById(String emitterId);

  void deleteAllEventCacheStartWithId(String prefix);
}

