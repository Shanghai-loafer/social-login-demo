plugins {
	java
	id("org.springframework.boot") version "3.0.0"
	id("io.spring.dependency-management") version "1.1.0"
	id("com.diffplug.spotless") version "6.14.0"
}

spotless {
	java {
		googleJavaFormat()
	}
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
	implementation("org.springdoc", "springdoc-openapi-starter-webmvc-ui","2.0.2")
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
		workingDir("./ui")
		commandLine("npm", "run", "build")
	}
}

/**
 * ReactモジュールをSpring側にもってくるタスク
 */
tasks.register("moveBuildModule") {
	doFirst {
		// 既存のファイルを削除
		project.delete("src/main/resources/templates/public")
		project.delete("src/main/resources/static")

		// 新しいモジュールをいれる
		copy {
			from("ui/build") {
				exclude("static")
			}
			into("src/main/resources/templates/public")
		}

		copy {
			from("ui/build/static")
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
