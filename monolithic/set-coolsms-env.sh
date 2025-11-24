#!/bin/bash

# CoolSMS API 환경변수 설정 스크립트
# 사용법: source ./set-coolsms-env.sh

echo "🔧 CoolSMS 환경변수를 설정합니다..."

# CoolSMS API Key (CoolSMS 콘솔에서 발급받은 API Key)
export COOLSMS_API_KEY="your_coolsms_api_key_here"

# CoolSMS API Secret (CoolSMS 콘솔에서 발급받은 API Secret)
export COOLSMS_API_SECRET="your_coolsms_api_secret_here"

# 발신번호 (CoolSMS에 등록된 발신번호)
export COOLSMS_SENDER="01012345678"

echo "✅ 환경변수 설정 완료!"
echo "📋 설정된 환경변수:"
echo "   - COOLSMS_API_KEY: $COOLSMS_API_KEY"
echo "   - COOLSMS_API_SECRET: $COOLSMS_API_SECRET"
echo "   - COOLSMS_SENDER: $COOLSMS_SENDER"

echo ""
echo "🚀 Spring Boot 애플리케이션을 시작하려면:"
echo "   ./gradlew bootRun"
