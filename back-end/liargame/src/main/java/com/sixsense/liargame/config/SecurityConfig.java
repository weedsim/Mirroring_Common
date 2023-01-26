package com.sixsense.liargame.config;

import com.sixsense.liargame.security.auth.JwtAuthenticationFilter;
import com.sixsense.liargame.security.auth.JwtExceptionFilter;
import com.sixsense.liargame.security.auth.JwtLoginFilter;
import com.sixsense.liargame.security.auth.JwtTokenProvider;
import com.sixsense.liargame.security.handler.OAuth2AuthenticationFailureHandler;
import com.sixsense.liargame.security.handler.OAuth2AuthenticationSuccessHandler;
import com.sixsense.liargame.security.oauth2.CustomOAuth2AuthorizationRequestRepository;
import com.sixsense.liargame.security.oauth2.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtTokenProvider jwtTokenProvider;
    private final CorsConfig corsConfig;
    private final CustomOAuth2AuthorizationRequestRepository<OAuth2AuthorizationRequest> customOAuth2AuthorizationRequestRepository;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager, CustomOAuth2UserService customOAuth2UserService) throws Exception {
        http
                .addFilter(corsConfig.corsFilter()) // cors 설정. 일단 전부 풀어놓음
                .httpBasic().disable() // 기본 로그인 화면 비활성화
                .formLogin().disable()
                .csrf().disable()   // csrf 보안 비활성화
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // jwt사용으로 session 비활성화
                .and()
                .authorizeRequests()
                .antMatchers("/swagger-ui/**", "/swagger-resources/", "/v2/api-docs").permitAll()
                .antMatchers("/login", "/users", "/users/duplicate").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                .authorizationEndpoint(authorize -> {
                    authorize.authorizationRequestRepository(
                            customOAuth2AuthorizationRequestRepository);
                })
                .userInfoEndpoint(userInfo -> {
                    userInfo.userService(customOAuth2UserService);
                })
                .loginProcessingUrl("/auth/login/*")
                .successHandler(oAuth2AuthenticationSuccessHandler)
                .failureHandler(oAuth2AuthenticationFailureHandler)
                .and()
                .logout() // 로그아웃 로직 추가해야함
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
                .addFilter(new JwtLoginFilter(authenticationManager, jwtTokenProvider))
                .addFilterBefore(new JwtExceptionFilter(), JwtLoginFilter.class);
        return http.build();
    }
}