/* SocialEntity DB 에 접근하여 Social ID, Social Type 가입된 사용자 조회
 * @Author : 김성현
 * @Date : 2025-11-01
 * @Version : 1.0
 * @*/

package com.ohgiraffers.geogieoddae.auth.command.repository;

import com.ohgiraffers.geogieoddae.auth.command.entity.user.SocialEntity;
import com.ohgiraffers.geogieoddae.auth.command.entity.user.SocialType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KakaoRepository extends JpaRepository<SocialEntity, Long> {
    // 소셜 ID와 소셜 타입으로 사용자를 찾는 메서드
    Optional<SocialEntity> findBySocialIdAndSocialType(String socialId, SocialType socialType);
}
