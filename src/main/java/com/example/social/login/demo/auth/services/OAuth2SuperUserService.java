package com.example.social.login.demo.auth.services;

import com.example.social.login.demo.auth.infrastructures.database.doma.entity.User;
import com.example.social.login.demo.auth.domains.repository.UserRepository;
import com.example.social.login.demo.auth.systems.SuperUser;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@Qualifier("Demo")
@RequiredArgsConstructor
public class OAuth2SuperUserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

  private final UserRepository userRepository;

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    final OAuth2UserService delegate = new DefaultOAuth2UserService();

    // Delegate to the default implementation for loading a user
    OAuth2User oAuth2User = delegate.loadUser(userRequest);

    OAuth2AccessToken accessToken = userRequest.getAccessToken();
    Set<GrantedAuthority> mappedAuthorities = new HashSet<>();

    // TODO
    // 1) Fetch the authority information from the protected resource using accessToken
    // 2) Map the authority information to one or more GrantedAuthority's and add it to
    // mappedAuthorities

    // 3) Create a copy of oidcUser but use the mappedAuthorities instead
    // oAuth2User = new DefaultOAuth2User(mappedAuthorities, oAuth2User.getAttributes(),
    // oAuth2User.getName());

    User user = userRepository.getUser("Github");

    // ここに共通のアレを渡す
    return new SuperUser(user.getName(), user.getPassword());
  }
}
