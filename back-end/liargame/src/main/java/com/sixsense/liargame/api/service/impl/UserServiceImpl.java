package com.sixsense.liargame.api.service.impl;

import com.sixsense.liargame.api.service.UserService;
import com.sixsense.liargame.security.auth.JwtTokenProvider;
import com.sixsense.liargame.security.auth.TokenInfo;
import com.sixsense.liargame.common.model.response.UserDto;
import com.sixsense.liargame.db.entity.User;
import com.sixsense.liargame.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    @Override
    public TokenInfo login(String email, String password) {
        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);

        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);

        return tokenInfo;
    }

    @Override
    public void signUp(UserDto userdto) {
        User user = User.builder()
                .email(userdto.getEmail())
                .name(userdto.getName())
                .password(userdto.getPassword())
                .role("guest")
                .build();
        userRepository.save(user);
    }

    /**
     * 이메일 또는 이름 중복 여부 확인
     * @param email 사용자 이메일
     * @param name 사용자 이름
     * @return 이메일 또는 이름 중복 여부 (true == 중복, false == 중복x)
     */
    @Override
    public boolean isDuplication(String email, String name) {
        if (email != null) return userRepository.existsByEmail(email);
        else return userRepository.existsByName(name);
    }


}