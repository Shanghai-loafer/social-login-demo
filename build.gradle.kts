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
		workingDir("./frontend")
		commandLine("npm", "run", "build")
	}
}

/**
 * ReactモジュールをSpringにもってくるタスク
 */
tasks.register("moveBuildModule") {
	doFirst {

		// 既存のファイルを削除
		project.delete("src/main/resources/templates/public")
		project.delete("src/main/resources/static")

		// 新しいモジュールをいれる
		copy {
			from("frontend/build") {
				exclude("static")
			}
			into("src/main/resources/templates/public")
		}

		copy {
			from("frontend/build/static")
			into("src/main/resources/static")
		}
	}
}

/**
 * サーバサイド側標準ビルドタスクにReactのビルドを関連付ける
 */
tasks.named("build") {
	dependsOn("buildReact")
	dependsOn("moveBuildModule")
}
tasks.named("bootRun") {
	dependsOn("buildReact")
	dependsOn("moveBuildModule")
}

/**
 * Task :processResourcesはあらゆるタスクに優先して実行されるため、
 * cdReactというタスクを用意してもbuild側が移動させないと、いつビルドが終わってるのかわからない
 */
