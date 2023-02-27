package com.example.social.login.demo.app.presentations.response;

import com.example.social.login.demo.app.presentations.request.Dummy;
import com.example.social.login.demo.auth.systems.modelmapper.TypeMapConfigurer;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
public class DummyToBirthStone extends TypeMapConfigurer<Dummy, BirthStone> {

    @Override
    protected void configure(TypeMap<Dummy, BirthStone> typeMap) {
//        typeMap.addMapping(Dummy::getHoge, BirthStone::setName);
//        typeMap.addMappings(mapping -> mapping.skip(BirthStone::setName));
        // これって他の値はベースになってしまうってことかなぁ。
    }
}
