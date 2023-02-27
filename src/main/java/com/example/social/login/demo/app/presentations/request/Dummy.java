package com.example.social.login.demo.app.presentations.request;

import com.example.social.login.demo.auth.systems.modelmapper.Mappable;
import lombok.Data;

@Data
public class Dummy implements Mappable {
    private String hoge;
}
