package com.ohgiraffers.geogieoddae.notification.command.repository;



import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Repository
public class EmitterRepositoryImpl implements EmitterRepository {

  private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();
  private final Map<String, Object> eventCache = new ConcurrentHashMap<>();

  @Override
  public SseEmitter save(String emitterId, SseEmitter sseEmitter) {//유저별 sseEmitter저장
    emitters.put(emitterId, sseEmitter);
    return sseEmitter;
  }

  @Override
  public void saveEventCache(String emitterId, Object event) {//데이터 임시 저장
    eventCache.put(emitterId, event);
  }

  @Override
  public Map<String, SseEmitter> findAllEmitterStartWithByMemberId(
      String memberId) {//유저의 모든 연결(sseEmitter) 반환
    return emitters.entrySet().stream()
        .filter(entry -> entry.getKey().startsWith(memberId))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
  }

  @Override
  public Map<String, Object> findAllEventCacheStartWithByMemberId(
      String memberId) {//유저의 모든 임시 데이터 반환
    return eventCache.entrySet().stream()
        .filter(entry -> entry.getKey().startsWith(memberId))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
  }

  //삭제
  @Override
  public void deleteById(String emitterId) {
    emitters.remove(emitterId);
  }

  @Override
  public void deleteAllEventCacheStartWithId(String memberIdPrefix) {
    eventCache.keySet().removeIf(key -> key.startsWith(memberIdPrefix));
  }
}

