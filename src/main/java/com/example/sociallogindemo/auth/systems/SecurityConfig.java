package com.example.sociallogindemo.auth.systems;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(customizer -> customizer
                        .requestMatchers(HttpMethod.POST, "/api/login").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(customizer -> customizer
                        // 最終的な認証済みユーザーオブジェクトをどう生成するのか
                        .defaultSuccessUrl("/")
                        .failureUrl("/login?error")
                        .loginPage("/login")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .permitAll()
                )
                .oauth2Login(customizer -> customizer
                        // 最終的な認証済みユーザーオブジェクトをどう生成するのか
                        .defaultSuccessUrl("/")
                        .failureUrl("/login?error")
                        .loginPage("/login").permitAll()
                )
                .logout(customizer -> customizer
                        .logoutSuccessUrl("/login?logout").permitAll()
                )
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
