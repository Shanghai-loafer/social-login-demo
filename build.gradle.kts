plugins {
	java
	id("org.springframework.boot") version "3.0.0"
	id("io.spring.dependency-management") version "1.1.0"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
	implementation ("org.springframework.security:spring-security-oauth2-jose")
	implementation ("io.jsonwebtoken:jjwt-api:0.11.5")
	implementation ("io.jsonwebtoken:jjwt-impl:0.11.5")
	implementation ("io.jsonwebtoken:jjwt-jackson:0.11.5")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation ("org.springframework.boot:spring-boot-starter-thymeleaf")
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")

}

tasks.withType<Test> {
	useJUnitPlatform()
}

/**
 * Gradleからnpm buildを実行するタスク
 */
tasks.register<Exec>("buildReact") {
	doFirst {

		// サーバサイドのほうから既存のモジュール削除しなくちゃ

		workingDir("./frontend")
		println(commandLine ("pwd"))
		commandLine("npm", "run", "build")
	}
}

/**
 * サーバサイド側標準ビルドタスクにReactのビルドを関連付ける
 */
tasks.named("build") {
	dependsOn("buildReact")
}
tasks.named("bootRun") {
	dependsOn("buildReact")
}

/**
 * Task :processResourcesはあらゆるタスクに優先して実行されるため、
 * cdReactというタスクを用意してもbuild側が移動させないと、いつビルドが終わってるのかわからない
 */
