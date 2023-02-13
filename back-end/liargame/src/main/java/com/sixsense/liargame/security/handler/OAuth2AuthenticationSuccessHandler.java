package com.sixsense.liargame.security.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sixsense.liargame.security.auth.JwtTokenProvider;
import com.sixsense.liargame.security.auth.TokenInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTemplate redisTemplate;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to ");
            return;
        }
        clearAuthenticationAttributes(request);
        TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication); // tokenInfo 만들어서

        redisTemplate.opsForValue()
                .set("RT:" + authentication.getName(), tokenInfo.getRefreshToken(), tokenInfo.getRefreshTokenExpirationTime(), TimeUnit.MILLISECONDS);

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

    @Override
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {
        return UriComponentsBuilder.fromUriString("http://localhost:3000/oauth/redirect")
                .build().toUriString();
    }
}
