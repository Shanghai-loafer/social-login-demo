package com.example.social.login.demo.auth.systems.modelmapper;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.internal.typetools.TypeResolver;

@Slf4j
public abstract class TypeMapConfigurer<S extends Mappable, D> {

    final void configureImpl(ModelMapper mapper) {
        Class<?>[] typeArguments = TypeResolver.resolveRawArguments(TypeMapConfigurer.class, getClass());

        configure(mapper.createTypeMap((Class<S>)typeArguments[0], (Class<D>)typeArguments[1]));

        log.info("マッピング登録: %s -> %s".formatted(typeArguments[0].getClass().getName(), typeArguments[1].getClass().getName()));
    }

    protected abstract void configure(TypeMap<S, D> typeMap);

}
