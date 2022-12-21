package com.example.sociallogindemo.auth.presentations.controller;

import com.example.sociallogindemo.auth.systems.Frontend;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class OAuth2ClientDemoController {

    private final Frontend frontend;

    @GetMapping("/login")
    String loginAsGet() {
        return "login";
    }

//    @GetMapping("{path:^(?!.*static).*$}/**")
//    public String any() {
//        return "index";
//    }

    @GetMapping("/")
    public String index() {

        /**
         * なにかしらの手段でもってユーザー情報やトークンをフロントエンドに渡す必要がある。
         */

        return "forward:/build/index.html";
    }
}
