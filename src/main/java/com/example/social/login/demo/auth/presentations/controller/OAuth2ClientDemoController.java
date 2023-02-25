package com.example.social.login.demo.auth.presentations.controller;

import com.example.social.login.demo.auth.systems.SuperUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class OAuth2ClientDemoController {

  @GetMapping("/login")
  String getLogin() {
    return "login";
  }

  @GetMapping("/")
  public String index(@AuthenticationPrincipal SuperUser user) {

    log.info(user.getName());

    /** なにかしらの手段でもってユーザー情報やトークンをフロントエンドに渡す必要がある。 */
    return "public/index";
  }
}
