package com.example.necklase.Model.Token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;


public class JwtUtils {
    private static final String SECRET_KEY = "fW8nIa82pExxznc7Z4qEtkdrPe4aKuO7ocF4s7EFB1kqFqNkKxayAeudMJXAwHa2";

    public static DecodedJWT decode(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            JWTVerifier verifier = JWT.require(algorithm).build();
            return verifier.verify(token);
        }catch (Exception e){
            return null;
        }
    }

}