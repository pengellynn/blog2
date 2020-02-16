package com.ripon.adminserver.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.ripon.adminserver.model.User;
import org.springframework.stereotype.Component;

@Component
public class Auth {
    public static String createToken(User user) {
        String token = "";
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            token = JWT.create()
                    .withAudience(user.getUsername())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            exception.printStackTrace();
        }
        return token;
    }
}
