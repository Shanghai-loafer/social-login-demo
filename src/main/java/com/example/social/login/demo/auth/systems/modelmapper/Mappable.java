package com.example.social.login.demo.auth.systems.modelmapper;

import org.modelmapper.ModelMapper;

public interface Mappable {

    ModelMapper mapper = new ModelMapper();

    default <T> T map(Class<T> clazz) {
        return mapper.map(this, clazz);
    }

}
