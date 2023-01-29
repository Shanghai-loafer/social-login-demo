package com.example.sociallogindemo.auth.services;

import com.example.sociallogindemo.auth.systems.SuperUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FromLoginSuperUserService implements UserDetailsService {

  private final PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    if (username == null) {
      throw new UsernameNotFoundException("empty");
    }

    // 本来ならDBアクセスしてパスワードを取得するところだが、サンプルなのでプログラム直書き
    String password = switch (username) {
      case "admin" -> passwordEncoder.encode("admin");
      default -> throw new UsernameNotFoundException("not found");
    };

    return new SuperUser(username, password);
  }
}
