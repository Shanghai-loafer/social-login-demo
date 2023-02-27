package com.example.social.login.demo.auth.systems.modelmapper;

import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public final class ModelMapperConfig {

    /**
     * https://modelmapper.org/user-manual/
     *
     * Springに内包しているmodelmapper周辺クラスは依存関係が怪しいらしいので、内製化する
     */

    private final List<TypeMapConfigurer<? extends Mappable, ?>> typeMapConfigurers;

    @PostConstruct
    private void init() {
        // 初期設定
        Mappable.mapper.getConfiguration().setSkipNullEnabled(true);
        Mappable.mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        // 各マッピングの登録
        typeMapConfigurers.forEach(config -> {
            config.configureImpl(Mappable.mapper);
        });

    }

}
