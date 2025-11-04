package com.ohgiraffers.geogieoddae.auth.command.service;

import com.ohgiraffers.geogieoddae.auth.command.dto.OAuthAttributes;
import com.ohgiraffers.geogieoddae.auth.command.entity.user.SocialEntity;
import com.ohgiraffers.geogieoddae.auth.command.entity.user.UserEntity;
import com.ohgiraffers.geogieoddae.auth.command.repository.KakaoRepository;
import com.ohgiraffers.geogieoddae.auth.command.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final KakaoRepository kakaoRepository;

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("LOG: CustomOAuth2UserService.loadUser() 시작");
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // 카카오에서 받은 정보 전체를 출력
        System.out.println("LOG: 카카오 응답 전체 속성: " + oAuth2User.getAttributes());

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        System.out.println("LOG: OAuth2 속성 정보 파싱 시작");
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        System.out.println("LOG: 파싱된 이메일: " + attributes.getEmail());

        // 카카오 토큰 및 만료시점 추출
        String kakaoAccessToken = userRequest.getAccessToken().getTokenValue();
        Instant accessTokenExpiresAt = userRequest.getAccessToken().getExpiresAt();
        LocalDateTime kakaoAccessTokenExpiresAt = accessTokenExpiresAt != null
                ? accessTokenExpiresAt.atZone(ZoneId.systemDefault()).toLocalDateTime()
                : null;

        String kakaoRefreshToken = userRequest.getAdditionalParameters().get("refresh_token") != null
                ? userRequest.getAdditionalParameters().get("refresh_token").toString()
                : null;
        // refresh_token 만료시점은 카카오에서 별도 제공하지 않을 수 있음
        LocalDateTime kakaoRefreshTokenExpiresAt = null;

        System.out.println("LOG: saveOrUpdate() 호출 시작");
        UserEntity user = saveOrUpdate(attributes, kakaoAccessToken, kakaoAccessTokenExpiresAt, kakaoRefreshToken, kakaoRefreshTokenExpiresAt);
        System.out.println("LOG: saveOrUpdate() 완료, User: " + user.getUserName());

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("ROLE_" + user.getUserRole().name())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    @Transactional
    public UserEntity saveOrUpdate(
            OAuthAttributes attributes,
            String kakaoAccessToken,
            LocalDateTime kakaoAccessTokenExpiresAt,
            String kakaoRefreshToken,
            LocalDateTime kakaoRefreshTokenExpiresAt
    ) {
        String socialId = attributes.getAttributes().get(attributes.getNameAttributeKey()).toString();
        Optional<SocialEntity> socialEntityOptional = kakaoRepository.findBySocialIdAndSocialType(socialId, attributes.getSocialType());

        UserEntity userEntity;
        if (socialEntityOptional.isPresent()) {
            userEntity = socialEntityOptional.get().getMember();
            // 토큰 정보 업데이트
            userEntity.setKakaoAccessToken(kakaoAccessToken);
            userEntity.setKakaoAccessTokenExpiresAt(kakaoAccessTokenExpiresAt);
            userEntity.setKakaoRefreshToken(kakaoRefreshToken);
            userEntity.setKakaoRefreshTokenExpiresAt(kakaoRefreshTokenExpiresAt);
            userRepository.save(userEntity);
        } else {
            Optional<UserEntity> userEntityOptional = userRepository.findByUserEmail(attributes.getEmail());
            if (userEntityOptional.isPresent()) {
                userEntity = userEntityOptional.get();
            } else {
                userEntity = attributes.toEntity();
            }
            // 신규 회원 토큰 정보 세팅
            userEntity.setKakaoAccessToken(kakaoAccessToken);
            userEntity.setKakaoAccessTokenExpiresAt(kakaoAccessTokenExpiresAt);
            userEntity.setKakaoRefreshToken(kakaoRefreshToken);
            userEntity.setKakaoRefreshTokenExpiresAt(kakaoRefreshTokenExpiresAt);
            userRepository.save(userEntity);

            SocialEntity newSocialEntity = SocialEntity.builder()
                    .socialId(socialId)
                    .socialType(attributes.getSocialType())
                    .member(userEntity)
                    .build();
            kakaoRepository.save(newSocialEntity);
        }
        return userEntity;
    }
}

