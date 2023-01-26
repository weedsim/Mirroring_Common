package com.sixsense.liargame.security.auth;

import com.sixsense.liargame.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceCustom implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceCustom(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // 로그인 필터에서 DB에 user정보가 있는지 확인하는 메서드
        return new UserDetailsCustom(userRepository.findByEmail(email).orElseThrow());
    }
}
