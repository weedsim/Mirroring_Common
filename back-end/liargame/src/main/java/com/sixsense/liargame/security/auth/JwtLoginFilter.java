package com.sixsense.liargame.security.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sixsense.liargame.common.model.request.LoginReq;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {
    private final JwtTokenProvider jwtTokenProvider;

    public JwtLoginFilter(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        super(authenticationManager);
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        ObjectMapper om = new ObjectMapper();
        LoginReq loginReq = null;
        try {
            loginReq = om.readValue(request.getInputStream(), LoginReq.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assert loginReq != null;
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginReq.getEmail(), loginReq.getPassword());

        return this.getAuthenticationManager().authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        TokenInfo tokenInfo = jwtTokenProvider.generateToken(authResult);
        ObjectMapper om = new ObjectMapper();
        String jsonStr = null;
        PrintWriter writer = response.getWriter();
        try {
            jsonStr = om.writeValueAsString(tokenInfo);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        //writer에 써줌(json으로 response)
        writer.print(jsonStr);
    }
}
