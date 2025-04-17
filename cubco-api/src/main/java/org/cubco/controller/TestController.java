package org.cubco.controller;

import org.cubco.util.JWTutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@PropertySource("classpath:security.properties")
public class TestController {

    @Autowired
    private JWTutil jwTutil;

    @GetMapping("/jwt")
    public String createJwt() {
        return jwTutil.createToken(20211476L, "ADMIN");
    }
}
