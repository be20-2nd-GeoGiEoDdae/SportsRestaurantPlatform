package com.geogieoddae.mainservice.notification.command.service;

import com.geogieoddae.mainservice.notification.command.repository.EmitterRepository;
import java.io.IOException;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
@RequiredArgsConstructor
public class AlarmSseService {

  private final EmitterRepository emitterRepository;
  //연결 지속시간 한시간
  private static final Long DEFAULT_TIMEOUT = 60L * 1000 * 60;


  public SseEmitter sseSubscribe(Long memberId, String lastEventId) {
    Long sseEmitterId = System.currentTimeMillis();
    String SseEmitterId = memberId + "-" + sseEmitterId;
    SseEmitter emitter = emitterRepository.save(SseEmitterId, new SseEmitter(DEFAULT_TIMEOUT));

    //SseEmitter sseEmitter = emitterRepository.save(SseEmitterId, new SseEmitter(DEFAULT_TIMEOUT));//시간 제한 없음
    emitter.onCompletion(() -> emitterRepository.deleteById(SseEmitterId));//연결 종료 시
    emitter.onTimeout(
        () -> emitterRepository.deleteById(SseEmitterId));//시간초과 시 (SseEmitter(0L)이라면 발생 안함)
    emitter.onError(
        (e) -> emitterRepository.deleteById(
            SseEmitterId));//에러(네트워크 에러,직렬화 등) 발생 시 콜백(emitterMap에서 SseEmitterId에 해당하는 연결 삭제)

    sendToClient(emitter,
        SseEmitterId, "EventStream Created. [memberId=" + memberId + "]");

    if (!lastEventId.isEmpty()) {
      Map<String, Object> events = emitterRepository.findAllEventCacheStartWithByMemberId(
          String.valueOf(memberId));
      events.entrySet().stream()
          .filter(
              entry -> lastEventId.compareTo(entry.getKey())
                  < 0)//문자열 비교라 10 <2 이렇게 나올 수 있어서 숫자 타입으로 비교 필요
          .forEach(entry -> sendToClient(emitter, entry.getKey(), entry.getValue()));
    }

    return emitter;//emitter객체 반환 시 메시지 발생 할 때 마다 클라이언트에게 응답 보냄
  }

  private void sendToClient(SseEmitter emitter, String id, Object data) {
    try {
      emitter.send(SseEmitter.event()
          .id(id)
          .name("sse")
          .data(data));
    } catch (IOException exception) {
      emitterRepository.deleteById(id);
      throw new RuntimeException("연결 오류!");
    }
  }

  public void send(Long userId, String message) {
    String id = String.valueOf(userId);//AlarmBoxId를 받아서 EventId로 활용 필요
    // var dto = AlarmBoxResponse.from(alarmBox);
    // userId기반 SssEmitter전부 가져옴
    Map<String, SseEmitter> sseEmitters = emitterRepository.findAllEmitterStartWithByMemberId(id);
    System.out.println("sse" + sseEmitters);
    sseEmitters.forEach(
        (key, emitter) -> {
          // 데이터 캐시 저장(유실된 데이터 처리하기 위함)
          emitterRepository.saveEventCache(key, message);
          // 데이터 전송
          sendToClient(emitter, key, message);
          System.out.println("key" + key);
        }
    );
  }
  public int disconnectByUserId(Long userId) {
    String prefix = String.valueOf(userId);
    Map<String, SseEmitter> targets =
        emitterRepository.findAllEmitterStartWithByMemberId(prefix);

    // 연결 종료 시도
    targets.forEach((key, emitter) -> {
      try { emitter.complete(); } catch (Exception ignore) {}
      emitterRepository.deleteById(key);
    });

    // 캐시도 정리(리플레이용 이벤트 캐시)
    emitterRepository.deleteAllEventCacheStartWithId(prefix);

    return targets.size();
  }
}