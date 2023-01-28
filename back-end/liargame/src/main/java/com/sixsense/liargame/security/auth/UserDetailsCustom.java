package com.sixsense.liargame.security.auth;

import com.sixsense.liargame.db.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Data
public class UserDetailsCustom implements OAuth2User {
    private final User user;
    private Map<String, Object> attributes;

    // 일반 로그인
    public UserDetailsCustom(User user) {
        this.user = user;
    }

    // 소셜 로그인
    public UserDetailsCustom(User user, Map<String, Object> attributes) {
        this.user = user;
        this.attributes = attributes;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities();
    }


    @Override
    public String getName() {
        return user.getEmail();
    }
}
