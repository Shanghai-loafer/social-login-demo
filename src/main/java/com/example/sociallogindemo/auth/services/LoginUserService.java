package com.example.sociallogindemo.auth.services;

import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUserService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null) {
            throw new UsernameNotFoundException("empty");
        }

        // 本来ならDBアクセスしてパスワードを取得するところだが、サンプルなのでプログラム直書き
        String password;
        switch (username) {
            case "admin":
                password = passwordEncoder.encode("admin");
                break;
            default:
                throw new UsernameNotFoundException("not found");
        }

        return new User(username, password, Collections.emptySet());
    }
}
