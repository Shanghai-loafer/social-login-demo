package com.example.social.login.demo.auth.presentations.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
@Tag(name = "Login", description = "API用のログイン")
@Slf4j
public class OAuth2ClientDemoAPIController {}
