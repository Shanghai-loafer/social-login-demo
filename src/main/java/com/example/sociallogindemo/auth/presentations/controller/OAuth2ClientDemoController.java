package com.example.sociallogindemo.auth.presentations.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class OAuth2ClientDemoController {

    @GetMapping("/login")
    String loginAsGet() {
        return "login";
    }

    @GetMapping("{path:^(?!.*static).*$}/**")
    public String any() {
        return "public/index";
    }

    @GetMapping("/")
    public String index() {

        // 処理自体は通ってるっぽいんだけど、どうにもページへの移動がうまく言ってない気がする。

        /**
         * なにかしらの手段でもってユーザー情報やトークンをフロントエンドに渡す必要がある。
         */

        return "public/index";
    }

}
