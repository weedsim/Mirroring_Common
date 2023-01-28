package com.sixsense.liargame.api.service.impl;

import com.sixsense.liargame.api.enums.Authority;
import com.sixsense.liargame.api.service.UserService;
import com.sixsense.liargame.common.model.Response;
import com.sixsense.liargame.common.model.request.UserRequestDto;
import com.sixsense.liargame.db.entity.User;
import com.sixsense.liargame.security.auth.JwtTokenProvider;
import com.sixsense.liargame.security.auth.TokenInfo;
import com.sixsense.liargame.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Response response;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public ResponseEntity<?> signUp(UserRequestDto.SignUp signUp) {
        if (userRepository.existsByEmail(signUp.getEmail())) {
            return response.fail("이미 회원가입된 이메일입니다.", HttpStatus.BAD_REQUEST);
        }

        User user = User.builder()
                .email(signUp.getEmail())
                .name(signUp.getName())
                .password(passwordEncoder.encode(signUp.getPassword()))
                .role(Collections.singletonList(Authority.ROLE_GUEST.name()))
                .build();
        userRepository.save(user);

        return response.success("회원가입에 성공했습니다.");
    }

    @Transactional
    @Override
    public ResponseEntity<?> login(UserRequestDto.Login login) {

        if (userRepository.findByEmail(login.getEmail()).orElse(null) == null) {
            return response.fail("해당하는 유저가 존재하지 않습니다.", HttpStatus.BAD_REQUEST);
        }

        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = login.toAuthentication();

        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);

        // TODO:: RefreshToken Redis 저장
        // 4. RefreshToken Redis 저장 (expirationTime 설정을 통해 자동 삭제 처리)
//        redisTemplate.opsForValue()
//                .set("RT:" + authentication.getName(), tokenInfo.getRefreshToken(), tokenInfo.getRefreshTokenExpirationTime(), TimeUnit.MILLISECONDS);

        return response.success(tokenInfo, "로그인에 성공했습니다.", HttpStatus.OK);
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
