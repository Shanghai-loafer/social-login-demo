package com.example.social.login.demo.auth.services;

import com.example.social.login.demo.auth.infrastructures.database.doma.entity.User;
import com.example.social.login.demo.auth.domains.repository.UserRepository;
import com.example.social.login.demo.auth.systems.SuperUser;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

@Service
@Qualifier("Demo")
@RequiredArgsConstructor
public class OidcSuperUserService implements OAuth2UserService<OidcUserRequest, OidcUser> {

  private final UserRepository userRepository;

  @Override
  public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {

    final OidcUserService delegate = new OidcUserService();

    // Delegate to the default implementation for loading a user
    OidcUser oidcUser = delegate.loadUser(userRequest);

    OAuth2AccessToken accessToken = userRequest.getAccessToken();
    Set<GrantedAuthority> mappedAuthorities = new HashSet<>();

    // TODO
    // 1) Fetch the authority information from the protected resource using accessToken
    // 2) Map the authority information to one or more GrantedAuthority's and add it to
    // mappedAuthorities

    // 3) Create a copy of oidcUser but use the mappedAuthorities instead
    oidcUser =
        new DefaultOidcUser(mappedAuthorities, oidcUser.getIdToken(), oidcUser.getUserInfo());

    User user = userRepository.getUser("Google");

    // ここに共通のアレを渡す
    return new SuperUser(user.getName(), user.getPassword());

  }
}
