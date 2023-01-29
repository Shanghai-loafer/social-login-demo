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

  public SuperUser(String username, String password) {
    // forDUMMY
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

  // これなに？
  @Override
  public OidcUserInfo getUserInfo() {
    return null;
  }

  // これなに？
  @Override
  public OidcIdToken getIdToken() {
    return null;
  }

  @Override
  public Map<String, Object> getAttributes() {
    return null;
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
