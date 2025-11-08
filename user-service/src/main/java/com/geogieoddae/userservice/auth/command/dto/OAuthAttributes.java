package com.geogieoddae.userservice.auth.command.dto;

import com.geogieoddae.userservice.auth.command.entity.user.SocialType;
import com.geogieoddae.userservice.auth.command.entity.user.UserEntity;
import com.geogieoddae.userservice.auth.command.entity.user.UserRole;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String nickname;
    private SocialType socialType;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String nickname, SocialType socialType) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.nickname = nickname;
        this.socialType = socialType;
    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        // registrationId에 "kakao"가 포함되어 있으면 카카오 로그인을 처리하는 ofKakao 메소드를 호출
        if ("kakao".equals(registrationId)) {
            return ofKakao("id", attributes);
        }
        // 다른 소셜 로그인(Google, Naver 등)을 추가할 경우 여기에 분기점을 추가
        return null;
    }

    private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        // 카카오 응답에서 사용자 정보가 포함된 kakao_account 맵을 추출
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        // kakao_account 맵에서 프로필 정보를 담은 profile 맵을 추출
        Map<String, Object> kakaoProfile = (Map<String, Object>) kakaoAccount.get("profile");

        return OAuthAttributes.builder()
                .name((String) kakaoProfile.get("nickname"))
                .email((String) kakaoAccount.get("email"))
                .nickname((String) kakaoProfile.get("nickname"))
                .socialType(SocialType.KAKAO)
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public UserEntity toEntity() {
        return UserEntity.builder()
                .userName(name)
                .userEmail(email)
                .userPhoneNumber("N/A") // 임시값
                .userAddress("N/A")   // 임시값
                .userRole(UserRole.USER) // 기본 권한
                .userRefreshToken("initial_token") // 초기값 설정
                .userRefreshTokenExpiresAt(LocalDateTime.now()) // 초기값 설정
                .build();
    }
}
