buildscript {
    repositories {
        mavenCentral()
    }
}



plugins {
    java
    id("org.springframework.boot") version "3.0.0"
    id("io.spring.dependency-management") version "1.1.0"
    id("org.domaframework.doma.compile") version "2.0.0" apply false
    id("org.domaframework.doma.codegen") version "2.0.0"
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
    implementation("mysql", "mysql-connector-java", "8.0.32")

    implementation("org.springdoc", "springdoc-openapi-starter-webmvc-ui", "2.0.2")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")

}

val generateRootPackageName = "com.example.sociallogindemo"

domaCodeGen {
//    getByName("dev") {
//        setUrl("jdbc:xxxx:/ホスト名/スキーマ名")
//        setUser("...")
//        setPassword("...")
//
//        setTemplateDir(file("${projectDir}/src/main/resources/templates/doma-gen/"))
//
//        entity {
//            setPackageName("${generateRootPackageName}.entity")
//        }
//        dao {
//            setPackageName("${generateRootPackageName}.dao")
//        }
//    }
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
