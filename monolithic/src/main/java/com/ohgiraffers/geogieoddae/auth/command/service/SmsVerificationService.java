package com.ohgiraffers.geogieoddae.auth.command.service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class SmsVerificationService {
    
    // Redis 대신 메모리 저장소 사용 (임시)
    private final ConcurrentHashMap<String, VerificationData> verificationStore = new ConcurrentHashMap<>();
    
    private static final int CODE_LENGTH = 6;
    private static final int CODE_EXPIRY_MINUTES = 5; // 5분 유효
    
    // 인증 데이터 저장용 내부 클래스
    private static class VerificationData {
        String code;
        LocalDateTime expiredAt;
        
        VerificationData(String code) {
            this.code = code;
            this.expiredAt = LocalDateTime.now().plusMinutes(CODE_EXPIRY_MINUTES);
        }
        
        boolean isExpired() {
            return LocalDateTime.now().isAfter(expiredAt);
        }
    }
    
    /**
     * 인증번호 발송 (개발모드: 콘솔 출력)
     */
    public boolean sendVerificationCode(String phoneNumber) {
        try {
            // 1. 인증번호 생성
            String verificationCode = generateVerificationCode();
            
            // 2. 메모리에 저장 (5분 유효)
            verificationStore.put(phoneNumber, new VerificationData(verificationCode));
            
            // 3. 개발모드: 콘솔에 인증번호 출력 (실제 SMS 발송 안 함)
            String message = "[거기어때] 인증번호: " + verificationCode + " (5분간 유효)";
            
            log.warn("🎯 ===========================================");
            log.warn("🎯 [개발모드] SMS 인증번호");
            log.warn("🎯 번호: {}", phoneNumber);
            log.warn("🎯 인증번호: {}", verificationCode);
            log.warn("🎯 메시지: {}", message);
            log.warn("🎯 유효시간: 5분");
            log.warn("🎯 ===========================================");
            
            // 개발 환경에서는 항상 성공으로 처리
            log.info("📱 콘솔 인증번호 발송 성공 - 번호: {}, 인증번호: {}", phoneNumber, verificationCode);
            
            // 만료된 데이터 정리
            cleanupExpiredCodes();
            
            return true;
            
        } catch (Exception e) {
            log.error("인증번호 발송 실패: {}", e.getMessage());
            return false;
        }
    }
    
    /**
     * 인증번호 확인
     */
    public boolean verifyCode(String phoneNumber, String inputCode) {
        try {
            VerificationData storedData = verificationStore.get(phoneNumber);
            
            if (storedData == null) {
                log.warn("인증번호 만료 또는 존재하지 않음: {}", phoneNumber);
                return false;
            }
            
            if (storedData.isExpired()) {
                verificationStore.remove(phoneNumber);
                log.warn("인증번호 만료: {}", phoneNumber);
                return false;
            }
            
            boolean isValid = storedData.code.equals(inputCode);
            
            if (isValid) {
                // 인증 성공 시 저장소에서 삭제
                verificationStore.remove(phoneNumber);
                log.info("✅ SMS 인증 성공: {}", phoneNumber);
            } else {
                log.warn("❌ SMS 인증 실패: {} (입력: {}, 저장: {})", phoneNumber, inputCode, storedData.code);
            }
            
            return isValid;
            
        } catch (Exception e) {
            log.error("인증번호 확인 실패: {}", e.getMessage());
            return false;
        }
    }
    
    /**
     * 6자리 랜덤 인증번호 생성
     */
    private String generateVerificationCode() {
        SecureRandom random = new SecureRandom();
        StringBuilder code = new StringBuilder();
        
        for (int i = 0; i < CODE_LENGTH; i++) {
            code.append(random.nextInt(10));
        }
        
        return code.toString();
    }
    
    /**
     * 남은 유효시간 확인 (초 단위)
     */
    public Long getRemainingTime(String phoneNumber) {
        VerificationData data = verificationStore.get(phoneNumber);
        if (data == null || data.isExpired()) {
            return 0L;
        }
        
        return java.time.Duration.between(LocalDateTime.now(), data.expiredAt).getSeconds();
    }
    
    /**
     * 만료된 인증번호 정리
     */
    private void cleanupExpiredCodes() {
        verificationStore.entrySet().removeIf(entry -> entry.getValue().isExpired());
    }
}
