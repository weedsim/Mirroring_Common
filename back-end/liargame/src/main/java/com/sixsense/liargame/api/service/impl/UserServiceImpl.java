package com.sixsense.liargame.api.service.impl;

import com.sixsense.liargame.api.enums.Authority;
import com.sixsense.liargame.api.service.UserService;
import com.sixsense.liargame.common.model.Response;
import com.sixsense.liargame.common.model.request.UserRequestDto;
import com.sixsense.liargame.common.model.response.UserInfoDto;
import com.sixsense.liargame.db.entity.User;
import com.sixsense.liargame.mail.MailHandler;
import com.sixsense.liargame.mail.TempKey;
import com.sixsense.liargame.security.auth.JwtTokenProvider;
import com.sixsense.liargame.security.auth.TokenInfo;
import com.sixsense.liargame.db.repository.UserRepository;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

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
    private final RedisTemplate redisTemplate;
    private final JavaMailSender mailSender;

    @Override
    public ResponseEntity<?> signUp(UserRequestDto.SignUp signUp) {
        if (userRepository.existsByEmail(signUp.getEmail())) {
            return response.fail("이미 회원가입된 이메일입니다.", HttpStatus.BAD_REQUEST);
        }

        String mailKey = new TempKey().getKey(30, false);

        User user = User.builder()
                .email(signUp.getEmail())
                .name(signUp.getName())
                .password(passwordEncoder.encode(signUp.getPassword()))
                .role(Collections.singletonList(Authority.ROLE_GUEST.name()))
                .mailKey(mailKey)
                .build();
        userRepository.save(user);
        try {
            MailHandler sendMail = new MailHandler(mailSender);
            sendMail.setSubject("[인증메일입니다.]");
            sendMail.setText("<h1>메일인증</h1>" +
                    "<br>아래 [이메일 인증 확인]을 눌러주세요." +
                    "<br><a href='http://localhost:5000/users/register-email?email=" + user.getEmail() +
                    "&mail-key=" + mailKey +
                    "' target='_blank'>이메일 인증 확인</a>");
            sendMail.setFrom("wjswndud53@gmail.com", "관리자");
            sendMail.setTo(user.getEmail());
            sendMail.send();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return response.success("회원가입에 성공했습니다.");
    }

    @Override
    public ResponseEntity<?> registerEmail(String email, String key){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("No authentication information."));


        String userKey = user.getMailKey();
        String msg;
        if(userKey.equals(key)) {
            msg = "이메일 인증 성공했습니다. TODO: 메인페이지로";
            // add ROLE_ADMIN
            user.getRole().remove(0);
            user.getRole().add(Authority.ROLE_USER.name());
            userRepository.save(user);
        }
        else msg = "이메일 인증 실패했습니다.";
        return response.success(msg);
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

        // 4. RefreshToken Redis 저장 (expirationTime 설정을 통해 자동 삭제 처리)
        redisTemplate.opsForValue()
                .set("RT:" + authentication.getName(), tokenInfo.getRefreshToken(), tokenInfo.getRefreshTokenExpirationTime(), TimeUnit.MILLISECONDS);

        return response.success(tokenInfo, "로그인에 성공했습니다.", HttpStatus.OK);
    }

    public ResponseEntity<?> reissue(UserRequestDto.Reissue reissue) {
        // 1. Refresh Token 검증
        try {
            if (!jwtTokenProvider.validateToken(reissue.getRefreshToken())) {
                return response.fail("Refresh Token 정보가 유효하지 않습니다.", HttpStatus.BAD_REQUEST);
            }
        } catch (ExpiredJwtException e) {
            return response.fail("토큰의 유효기간이 지났습니다.", HttpStatus.UNAUTHORIZED);
        }

        // 2. Access Token 에서 User email 을 가져옵니다.
        Authentication authentication = jwtTokenProvider.getAuthentication(reissue.getAccessToken());

        // 3. Redis 에서 User email 을 기반으로 저장된 Refresh Token 값을 가져옵니다.
        String refreshToken = (String)redisTemplate.opsForValue().get("RT:" + authentication.getName());
        // (추가) 로그아웃되어 Redis 에 RefreshToken 이 존재하지 않는 경우 처리
        if(ObjectUtils.isEmpty(refreshToken)) {
            return response.fail("잘못된 요청입니다.", HttpStatus.BAD_REQUEST);
        }
        if(!refreshToken.equals(reissue.getRefreshToken())) {
            return response.fail("Refresh Token 정보가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
        }

        // 4. 새로운 토큰 생성
        TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);

        // 5. RefreshToken Redis 업데이트
        redisTemplate.opsForValue()
                .set("RT:" + authentication.getName(), tokenInfo.getRefreshToken(), tokenInfo.getRefreshTokenExpirationTime(), TimeUnit.MILLISECONDS);

        return response.success(tokenInfo, "Token 정보가 갱신되었습니다.", HttpStatus.OK);
    }

    public ResponseEntity<?> logout(UserRequestDto.Logout logout) {
        // 1. Refresh Token 검증
        try {
            if (!jwtTokenProvider.validateToken(logout.getRefreshToken())) {
                return response.fail("잘못된 요청입니다.", HttpStatus.BAD_REQUEST);
            }
        } catch (ExpiredJwtException e) {
            return response.fail("토큰의 유효기간이 지났습니다.", HttpStatus.UNAUTHORIZED);
        }
        // 2. Access Token 에서 User email 을 가져옵니다.
        Authentication authentication = jwtTokenProvider.getAuthentication(logout.getAccessToken());
        // 3. Redis 에서 해당 User email 로 저장된 Refresh Token 이 있는지 여부를 확인 후 있을 경우 삭제합니다.
        if (redisTemplate.opsForValue().get("RT:" + authentication.getName()) != null) {
            // Refresh Token 삭제
            redisTemplate.delete("RT:" + authentication.getName());
        }

        // 4. 해당 Access Token 유효시간 가지고 와서 BlackList 로 저장하기
        System.out.println(logout.getAccessToken());
        Long expiration = jwtTokenProvider.getExpiration(logout.getAccessToken());
        redisTemplate.opsForValue()
                .set(logout.getAccessToken(), "logout", expiration, TimeUnit.MILLISECONDS);

        return response.success("로그아웃 되었습니다.");
    }

    @Override
    @Transactional
    public ResponseEntity<?> updateName(UserRequestDto.ModifyName name) {
        Authentication authentication = jwtTokenProvider.getAuthentication(name.getAccessToken());
        User user = userRepository.findByEmail(authentication.getName()).get();
        user.updateName(name.getName());
        return response.success("이름이 변경되었습니다.");
    }

    @Override
    @Transactional
    public ResponseEntity<?> updatePassword(UserRequestDto.ModifyPassword password) {
        Authentication authentication = jwtTokenProvider.getAuthentication(password.getAccessToken());
        User user = userRepository.findByEmail(authentication.getName()).get();
        user.updatePassword(passwordEncoder.encode(password.getPassword()));
        return response.success("비밀번호가 변경되었습니다.");
    }

    @Override
    public ResponseEntity<?> getUserInfo(UserRequestDto.UserInfo userInfo) {
        Authentication authentication = jwtTokenProvider.getAuthentication(userInfo.getAccessToken());
        User user = userRepository.findByEmail(authentication.getName()).get();
        UserInfoDto userInfoDto = UserInfoDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .build();
        return new ResponseEntity<UserInfoDto>(userInfoDto, HttpStatus.OK);
    }

    public ResponseEntity<?> authority() {
        // SecurityContext에 담겨 있는 authentication userEamil 정보
        String userEmail = getCurrentUserEmail();

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("No authentication information."));

        // add ROLE_ADMIN
        user.getRole().add(Authority.ROLE_USER.name());
        userRepository.save(user);

        return response.success();
    }

    public static String getCurrentUserEmail() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getName() == null) {
            throw new RuntimeException("No authentication information.");
        }
        return authentication.getName();
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
