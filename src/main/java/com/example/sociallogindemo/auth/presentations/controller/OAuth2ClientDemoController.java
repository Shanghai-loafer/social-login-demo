package com.example.sociallogindemo.app.presentations.controller;

import com.example.sociallogindemo.app.presentations.response.OAuth2ClientDemoResponse;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class OAuth2ClientDemoController {

    @GetMapping("/")
    public OAuth2ClientDemoResponse index(OAuth2AuthenticationToken authentication,
            @AuthenticationPrincipal OAuth2User oauth2User) {

        // これは何に使うんだろう？トークンってことはワンタイムトークン？　OAuth2AuthenticationToken

        //属性はgithubに特化しています。
        // googleとプロパティを共有していくには特殊なことをしないといけないのでは？
//        model.addAttribute("name", oauth2User.getAttributes().get("name"));
//        model.addAttribute("location", oauth2User.getAttributes().get("location"));
//        model.addAttribute("bio", oauth2User.getAttributes().get("bio"));
//        model.addAttribute("blog", oauth2User.getAttributes().get("blog"));

        return new OAuth2ClientDemoResponse(oauth2User.getAttributes().get("name").toString());
    }
}
