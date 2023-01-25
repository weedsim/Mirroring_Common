package com.sixsense.liargame.security.oauth2;


import com.nimbusds.oauth2.sdk.util.StringUtils;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class OAuth2CookieAuthorizationRequestRepository<T extends OAuth2AuthorizationRequest> implements
        AuthorizationRequestRepository<OAuth2AuthorizationRequest> {

    public static final String OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME = "oauth2_auth_request";
    public static final String REDIRECT_URI_PARAM_COOKIE_NAME = "redirect_uri";
    private static final char PATH_DELIMITER = '/';
    private static final int cookieExpireSeconds = 180;
    private final ClientRegistrationRepository clientRegistrationRepository;

    public OAuth2CookieAuthorizationRequestRepository(
            ClientRegistrationRepository clientRegistrationRepository) {
        this.clientRegistrationRepository = clientRegistrationRepository;
    }

    //client registration 설정을 가지고
    //토큰의 요청 주소를 만드는 메소드이다.
    //RequestResolver의 로직을 따라간다.
    private static String expandRedirectUri(HttpServletRequest request, ClientRegistration clientRegistration, String action) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("registrationId", clientRegistration.getRegistrationId());
        // @formatter:off
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(
                        UrlUtils.buildFullRequestUrl(request))
                .replacePath(request.getContextPath())
                .replaceQuery(null)
                .fragment(null)
                .build();
        // @formatter:on

        String scheme = uriComponents.getScheme();
        uriVariables.put("baseScheme", (scheme != null) ? scheme : "");
        String host = uriComponents.getHost();
        uriVariables.put("baseHost", (host != null) ? host : "");
        // following logic is based on HierarchicalUriComponents#toUriString()
        int port = uriComponents.getPort();
        uriVariables.put("basePort", (port == -1) ? "" : ":" + port);
        String path = uriComponents.getPath();
        if (org.springframework.util.StringUtils.hasLength(path)) {
            if (path.charAt(0) != PATH_DELIMITER) {
                path = PATH_DELIMITER + path;
            }
        }
        uriVariables.put("basePath", (path != null) ? path : "");
        uriVariables.put("baseUrl", uriComponents.toUriString());
        uriVariables.put("action", (action != null) ? action : "");
        return UriComponentsBuilder.fromUriString(clientRegistration.getRedirectUri())
                .buildAndExpand(uriVariables)
                .toUriString();
    }

    // oauth2_auth_request 이름의 쿠키를 찾아 반환
    @Override
    public OAuth2AuthorizationRequest loadAuthorizationRequest(HttpServletRequest request) {
        System.out.println("load");
        return Optional.ofNullable(CookieUtils.getCookie(request, OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME))
                .map(cookie -> CookieUtils.deserialize(cookie, OAuth2AuthorizationRequest.class))
                .orElse(null);
    }

    //authorizationrequest를 serialize하여
    //oauth2_auth_request와 redirect_uri 쿠키 만들어 저장
    @Override
    public void saveAuthorizationRequest(OAuth2AuthorizationRequest authorizationRequest, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("save");
        CookieUtils.addCookie(response, OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME,
                CookieUtils.serialize(authorizationRequest), cookieExpireSeconds);
        String redirectUriAfterLogin = request.getParameter(REDIRECT_URI_PARAM_COOKIE_NAME);
        if (StringUtils.isNotBlank(redirectUriAfterLogin)) {
            CookieUtils.addCookie(response, REDIRECT_URI_PARAM_COOKIE_NAME, redirectUriAfterLogin,
                    cookieExpireSeconds);
        }
    }

    //쿠키의 authorizationRequest 삭제
    //쿠키에 AuthorizationRequest가 없는 상태에서 loginFilter가 이 메소드를 호출할 수 있으므로
    //request에서 적절한 AuthorizationRequest를 생성하여 반환해줘야 함.
    //AuthorizationRequest 생성 로직은 RequestResolver를 그대로 따라간다.
    @Override
    public OAuth2AuthorizationRequest removeAuthorizationRequest(HttpServletRequest request) {
        System.out.println("removeAuthorizationRequest");
        Assert.notNull(request, "request cannot be null");

        OAuth2AuthorizationRequest originalRequest = this.loadAuthorizationRequest(request);

        //만약 쿠키에 original request가 없을 경우
        //kakao의 client registration을 불러와서 그에 맞는 authorizationRequest 생성
        if (originalRequest == null) {
            String registrationId = request.getParameter("state");
            ClientRegistration clientRegistration = clientRegistrationRepository.findByRegistrationId(registrationId);
            if (clientRegistration == null) {
                throw new IllegalArgumentException("Invalid Client Registration with Id: " + registrationId);
            }
            OAuth2AuthorizationRequest.Builder builder = OAuth2AuthorizationRequest.authorizationCode()
                    .attributes((attrs) ->
                            attrs.put(OAuth2ParameterNames.REGISTRATION_ID,
                                    clientRegistration.getRegistrationId()));

            String redirectUriStr = expandRedirectUri(request, clientRegistration, "login");

            builder.clientId(clientRegistration.getClientId())
                    .authorizationUri(clientRegistration.getProviderDetails().getAuthorizationUri())
                    .redirectUri(redirectUriStr)
                    .scopes(clientRegistration.getScopes())
                    .state(request.getParameter("state"));

            originalRequest = builder.build();
        }

        return originalRequest;
    }

}