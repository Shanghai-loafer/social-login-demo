package com.example.sociallogindemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SocialLoginDemoApplication {

	/**
	 * やりたいこと
	 * 1. ソーシャルログインとフォームログインで同じ挙動をする(二つのログイン形式を両立しつつクレデンシャルは統一の形を提供する)
	 * 		OAuth2User, FormLoginUser -> DemoAppUserとして解釈。はたしてこれが適切な形かはわからん
	 * 2. Spring OAuthを利用しながら、フロントを分離する
	 * 		a. ログインページのみをサーバで提供して、ログイン後にSPAページに送るような形？
	 * 		b. ページへ飛ばすことは
	 */

	public static void main(String[] args) {
		SpringApplication.run(SocialLoginDemoApplication.class, args);
	}

}
