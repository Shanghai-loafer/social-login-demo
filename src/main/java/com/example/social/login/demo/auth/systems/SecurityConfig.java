package com.example.social.login.demo.auth.systems;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

  // 名称指定しなくてもDIされてくれるっぽいが、サービス側には名称を付与しておく必要があるっぽい
  @Qualifier("Demo")
  private final OAuth2UserService<OidcUserRequest, OidcUser> oidcSuperUserService;

  @Qualifier("Demo")
  private final OAuth2UserService<OAuth2UserRequest, OAuth2User> oauth2SuperUserService;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.authorizeHttpRequests(
            customizer ->
                customizer
                    .requestMatchers(HttpMethod.POST, "/api/login")
                    .permitAll()
                    .requestMatchers("/api/swagger-ui.html")
                    .permitAll()
                    .anyRequest()
                    .authenticated())
        .formLogin(
            customizer ->
                customizer
                    // 最終的な認証済みユーザーオブジェクトをどう生成するのか
                    .defaultSuccessUrl("/")
                    .failureUrl("/login?error")
                    .loginPage("/login")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .permitAll())
        .oauth2Login(
            customizer ->
                customizer
                    .defaultSuccessUrl("/")
                    .failureUrl("/login?error")
                    .loginPage("/login")
                    .permitAll()
                    .userInfoEndpoint(
                        customizer2 ->
                            customizer2
                                .oidcUserService(oidcSuperUserService)
                                .userService(oauth2SuperUserService)))
        .logout(
            customizer ->
                customizer
                    .deleteCookies("JSESSIONID")
                    .logoutSuccessUrl("/login?logout")
                    .permitAll())
        .sessionManagement(
            customizer ->
                customizer
                    .maximumSessions(1)
                    .maxSessionsPreventsLogin(false)
                    .expiredUrl("/login?expired"))
        .build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
