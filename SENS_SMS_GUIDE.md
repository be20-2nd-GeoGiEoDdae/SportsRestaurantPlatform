# 네이버 클라우드 SENS SMS API 통합 가이드

## 1. 네이버 클라우드 플랫폼 설정

### 1-1. 계정 생성 및 프로젝트 설정
1. [네이버 클라우드 플랫폼](https://www.ncloud.com/) 접속
2. 회원가입 및 본인인증 완료
3. 결제수단 등록 (무료 크레딧 제공)

### 1-2. SENS 서비스 활성화
1. 콘솔 로그인 → **AI·Application Service** → **SENS**
2. **SMS** 클릭하여 서비스 활성화
3. **프로젝트 생성**

### 1-3. API 키 발급
1. **API Key 관리** 메뉴
2. **신규 API Key 생성**
3. **Access Key ID**와 **Secret Key** 기록

### 1-4. 발신번호 등록 (필수)
1. **SMS** → **발신번호 관리**
2. **발신번호 등록** 클릭
3. 본인 명의 휴대폰 번호 입력 및 인증
4. 승인 완료까지 대기 (보통 2-3시간 소요)

### 1-5. Service ID 확인
1. **SMS** → **프로젝트 설정**
2. **Service ID** 복사

## 2. 환경 변수 설정

### 2-1. 운영 환경 (production)
```bash
# 환경 변수 설정
export NCP_ACCESS_KEY=your_actual_access_key
export NCP_SECRET_KEY=your_actual_secret_key
export NCP_SMS_SERVICE_ID=ncp:sms:kr:123456789:your_service_id
export NCP_SENDER_PHONE=01012345678
```

### 2-2. 개발 환경 (development)
개발 환경에서는 SENS 설정 없이도 작동합니다:
- SENS 설정이 없으면 콘솔에 인증번호가 출력됩니다
- 실제 SMS는 발송되지 않습니다

### 2-3. IntelliJ IDEA 환경 변수 설정
1. **Run/Debug Configurations** 열기
2. **Environment variables** 섹션
3. 다음 변수들 추가:
   ```
   NCP_ACCESS_KEY=your_access_key
   NCP_SECRET_KEY=your_secret_key
   NCP_SMS_SERVICE_ID=ncp:sms:kr:123456789:your_service_id
   NCP_SENDER_PHONE=01012345678
   ```

## 3. 애플리케이션 실행

### 3-1. 개발 모드 (SENS 설정 없음)
```bash
cd monolithic
./gradlew bootRun
```
콘솔에 인증번호가 출력됩니다.

### 3-2. 운영 모드 (SENS 설정 있음)
```bash
cd monolithic
export NCP_ACCESS_KEY=your_access_key
export NCP_SECRET_KEY=your_secret_key
export NCP_SMS_SERVICE_ID=your_service_id
export NCP_SENDER_PHONE=your_phone
./gradlew bootRun
```
실제 SMS가 발송됩니다.

## 4. 테스트 방법

### 4-1. 개발 환경 테스트
1. 애플리케이션 실행 (환경 변수 없이)
2. 회원가입 페이지에서 전화번호 입력
3. **인증번호 발송** 버튼 클릭
4. 콘솔에서 인증번호 확인
5. 인증번호 입력 후 **인증 확인** 클릭

### 4-2. 운영 환경 테스트
1. 네이버 클라우드에서 발신번호 승인 완료 확인
2. 환경 변수 설정 후 애플리케이션 실행
3. 실제 휴대폰 번호로 테스트
4. SMS 수신 확인

## 5. 문제 해결

### 5-1. 일반적인 오류
- **발신번호 미등록**: 네이버 클라우드에서 발신번호를 먼저 등록해야 함
- **API 키 오류**: Access Key와 Secret Key 확인
- **Service ID 오류**: 정확한 Service ID 형식 확인 (ncp:sms:kr:...)

### 5-2. 로그 확인
애플리케이션 실행 시 다음 로그들을 확인:
```
📱 [개발모드] SMS 인증번호 - 번호: 01012345678, 인증번호: 123456
📱 SENS SMS 발송 결과 - 번호: 01012345678, 성공: true
```

## 6. 비용 안내

- **SMS 발송비**: 건당 약 8~12원
- **무료 크레딧**: 신규 가입 시 일정 금액 제공
- **테스트**: 개발 환경에서는 비용 발생 없음

## 7. 보안 주의사항

- API 키는 절대 코드에 직접 입력하지 마세요
- 환경 변수나 안전한 키 관리 시스템 사용
- 발신번호는 본인 명의로만 등록 가능
- 스팸 방지를 위한 발송량 제한 있음
