package com.sixsense.liargame.common.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class UserRequestDto {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class SignUp {

        private String email;

        private String password;

        private String name;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Login {
        private String email;

        private String password;

        public UsernamePasswordAuthenticationToken toAuthentication() {
            return new UsernamePasswordAuthenticationToken(email, password);
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Reissue {
        private String accessToken;

        private String refreshToken;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Logout {
        private String accessToken;

        private String refreshToken;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Modify {

        private String accessToken;

        private String password;

        private String name;

        public Modify(String password, String name) {
            this.password = password;
            this.name = name;
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class UserInfo {
        private String accessToken;
    }
}
