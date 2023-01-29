package com.example.sociallogindemo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(title = "social-login-demo", description = "汎用的なWebアプリケーションの雛形", version = "v1"))
public class SocialLoginDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(SocialLoginDemoApplication.class, args);
  }
}
