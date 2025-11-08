/* 사용자 정보 조회 담당 - Spring Security <-> DB 간 다리 역할
 * @Author : 김성현
 * @Date : 2025-10-31
 * @Version : 1.0
 * @*/

package com.geogieoddae.userservice.admin.command.security;

import com.geogieoddae.userservice.admin.command.entity.AdminEntity;
import com.geogieoddae.userservice.admin.command.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AdminDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;

    // Spring Security 가 사용자 인증해야할 때 호출하는 메서드
    @Override
    public UserDetails loadUserByUsername(String adminId) throws UsernameNotFoundException {
        AdminEntity admin = adminRepository.findByAdminId(adminId)
                .orElseThrow(() -> new UsernameNotFoundException("관리자를 찾을 수 없습니다 : " + adminId));

        return new AdminDetails(
                admin.getAdminId(),
                admin.getAdminPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"))
                // 일단 'ROLE_ADMIN' 권한을 부여함
        );

    }
}
