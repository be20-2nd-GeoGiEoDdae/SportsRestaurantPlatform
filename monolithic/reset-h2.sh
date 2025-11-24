#!/bin/bash
# H2 데이터베이스 초기화 스크립트

echo "H2 데이터베이스 초기화 중..."

# 기존 H2 파일들 삭제
rm -f ./data/testdb.mv.db
rm -f ./data/testdb.trace.db

echo "H2 데이터베이스 파일이 삭제되었습니다."
echo "애플리케이션을 재시작하면 새로운 데이터베이스가 생성됩니다."
