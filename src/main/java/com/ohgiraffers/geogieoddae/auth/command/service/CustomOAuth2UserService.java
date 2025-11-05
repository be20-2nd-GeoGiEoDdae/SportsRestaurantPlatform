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

        System.out.println("LOG: saveOrUpdate() 호출 시작");
        UserEntity user = saveOrUpdate(attributes);
        System.out.println("LOG: saveOrUpdate() 완료, User: " + user.getUserName());

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("ROLE_" + user.getUserRole().name())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    @Transactional
    public UserEntity saveOrUpdate(OAuthAttributes attributes) {
        String socialId = attributes.getAttributes().get(attributes.getNameAttributeKey()).toString();
        Optional<SocialEntity> socialEntityOptional = kakaoRepository.findBySocialIdAndSocialType(socialId, attributes.getSocialType());

        UserEntity userEntity;
        if (socialEntityOptional.isPresent()) {
            System.out.println("LOG: 기존 소셜 연동 회원입니다.");
            userEntity = socialEntityOptional.get().getMember();
        } else {
            System.out.println("LOG: 신규 소셜 연동입니다. 이메일로 기존 회원 여부 확인");
            Optional<UserEntity> userEntityOptional = userRepository.findByUserEmail(attributes.getEmail());
            if (userEntityOptional.isPresent()) {
                System.out.println("LOG: 이메일이 같은 기존 회원이 존재합니다. 소셜 정보와 연결합니다.");
                userEntity = userEntityOptional.get();
            } else {
                System.out.println("LOG: 완전 신규 회원입니다. 새로 생성합니다.");
                userEntity = attributes.toEntity();
                userRepository.save(userEntity);
            }

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

