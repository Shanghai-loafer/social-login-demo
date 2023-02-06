package com.example.sociallogindemo.auth.systems;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;

/**
 * このままだと、ユーザ毎のメソッドが剥き出しなので利便性をあげるようにしないと
 *
 * <p>なにかしらアイデアが必要だな。
 */
public class SuperUser extends User implements OidcUser, OAuth2User {

  /**
   * 権限が必要のないユーザに対してはこれ そもそもSPAでつくっている都合上、権限というものが正しくどうさするのか非常に怪しいな。 REST
   * API経由のリクエストだけは権限付とかには一応できるのか。
   *
   * @param username
   * @param password
   */
  public SuperUser(String username, String password) {
    super(username, password, Collections.emptySet());
  }

  public SuperUser(
      String username, String password, Collection<? extends GrantedAuthority> authorities) {
    super(username, password, authorities);
  }

  @Override
  public Map<String, Object> getClaims() {
    return null;
  }

  /**
   * これ、何に使うの？
   *
   * @return
   */
  @Override
  public OidcUserInfo getUserInfo() {
    throw new RuntimeException("呼び出さないでー");
  }

  /**
   * これ、何に使うの？
   *
   * @return
   */
  @Override
  public OidcIdToken getIdToken() {
    throw new RuntimeException("呼び出さないでー");
  }

  @Override
  public Map<String, Object> getAttributes() {
    throw new RuntimeException("呼び出さないでー");
  }

  @Override
  public Collection<GrantedAuthority> getAuthorities() {
    return super.getAuthorities();
  }

  @Override
  public String getPassword() {
    return super.getPassword();
  }

  @Override
  public String getName() {
    return super.getUsername();
  }
}
