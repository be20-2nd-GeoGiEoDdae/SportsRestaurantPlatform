#!/bin/bash

# CoolSMS 실제 API 설정 스크립트
# 이 스크립트를 사용하여 실제 CoolSMS API 키와 발신번호를 설정하세요.

echo "🚀 CoolSMS 실제 API 설정을 시작합니다."
echo ""

echo "📝 CoolSMS 콘솔 (https://console.coolsms.co.kr)에서 다음 정보를 확인하세요:"
echo "1. API Key"
echo "2. API Secret"
echo "3. 발신번호 (사전에 등록된 번호)"
echo ""

# API Key 입력받기
read -p "🔑 CoolSMS API Key를 입력하세요: " COOLSMS_API_KEY
read -p "🔒 CoolSMS API Secret을 입력하세요: " COOLSMS_API_SECRET
read -p "📱 발신번호를 입력하세요 (예: 01012345678): " COOLSMS_SENDER

# 입력값 검증
if [[ -z "$COOLSMS_API_KEY" || -z "$COOLSMS_API_SECRET" || -z "$COOLSMS_SENDER" ]]; then
    echo "❌ 모든 값을 입력해주세요."
    exit 1
fi

# 환경변수 설정 파일 생성
ENV_FILE="coolsms-env.sh"
cat > "$ENV_FILE" << EOF
#!/bin/bash
# CoolSMS 환경변수 설정 파일
# 이 파일을 source 명령으로 로드하거나 .bashrc/.zshrc에 추가하세요.

export COOLSMS_API_KEY="$COOLSMS_API_KEY"
export COOLSMS_API_SECRET="$COOLSMS_API_SECRET"
export COOLSMS_SENDER="$COOLSMS_SENDER"

echo "✅ CoolSMS 환경변수가 설정되었습니다."
echo "📱 발신번호: $COOLSMS_SENDER"
EOF

chmod +x "$ENV_FILE"

echo ""
echo "✅ CoolSMS 설정이 완료되었습니다!"
echo ""
echo "📁 다음 파일이 생성되었습니다: $ENV_FILE"
echo ""
echo "🔧 사용 방법:"
echo "1. 환경변수 로드: source $ENV_FILE"
echo "2. 또는 .zshrc에 추가: echo 'source $(pwd)/$ENV_FILE' >> ~/.zshrc"
echo ""
echo "⚠️  주의사항:"
echo "- API Key와 Secret은 절대 Git에 커밋하지 마세요."
echo "- 발신번호는 CoolSMS 콘솔에서 사전 등록되어야 합니다."
echo "- SMS 발송 시 요금이 부과됩니다."
echo ""
echo "🚀 이제 다음 명령으로 애플리케이션을 실행하세요:"
echo "   source $ENV_FILE && ./gradlew bootRun"
