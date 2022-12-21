package com.example.sociallogindemo.auth.systems;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class Frontend {

    @Value("${app.frontend.url}")
    String url;

}
