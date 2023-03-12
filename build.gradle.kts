buildscript {
    repositories {
        mavenCentral()
    }

    dependencies {
        classpath("mysql", "mysql-connector-java", "8.0.32")
    }
}

plugins {
    java
    id("org.springframework.boot") version "3.0.0"
    id("io.spring.dependency-management") version "1.1.0"
    id("org.domaframework.doma.compile") version "2.0.0"
    id("org.domaframework.doma.codegen") version "2.0.0"
}

repositories {
    mavenCentral()
}

group = "com.example.social.login.demo"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

dependencies {
    val domaVersion: String by project
    annotationProcessor("org.seasar.doma:doma-processor:${domaVersion}")
    implementation("org.seasar.doma:doma-core:${domaVersion}")
    implementation("org.seasar.doma:doma-slf4j:${domaVersion}")
    implementation("org.seasar.doma.boot", "doma-spring-boot-starter", "1.7.0")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    implementation("org.springframework.security:spring-security-oauth2-jose")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.modelmapper:modelmapper:2.3.5")
    implementation("mysql", "mysql-connector-java", "8.0.32")

    implementation("org.springdoc", "springdoc-openapi-starter-webmvc-ui", "2.0.2")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")

}

val DOMA_DIR: String by project
val DB_URL: String by project
val DB_USERNAME: String by project
val DB_PASSWORD: String by project
val DB_SCHEMA: String by project
domaCodeGen {
    register("dev") {
        url.set("${DB_URL}/${DB_SCHEMA}")
        user.set("${DB_USERNAME}")
        password.set("${DB_PASSWORD}")

        templateDir.set(file("${projectDir}/src/main/resources/templates/doma-gen/"))

        entity {
            packageName.set("${DOMA_DIR}.entity")
        }
        dao {
            packageName.set("${DOMA_DIR}.dao")
        }
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

/**
 * Reactのビルドを実行するタスク
 */
tasks.register<Exec>("buildReact") {
    doFirst {
        println("ビルド開始")
        workingDir("./ui")
        commandLine("npm", "run", "build")
    }
    doLast {
        println("ビルドファイル移動")
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

        // 静的リソースをいれる
        copy {
            from("ui/build/static")
            into("src/main/resources/static")
        }
    }
}

/**
 * サーバサイド側標準ビルドタスクにReactのビルドを関連付ける
 * これ本当はコンパイル前に依存してないといけないんだけどなー
 * これもしかしてdomagenのタスクが追加された結果としておかしくなったのかな？
 */
tasks.named("build") {
    dependsOn("buildReact")
}
tasks.named("bootRun") {
    dependsOn("buildReact")
}
