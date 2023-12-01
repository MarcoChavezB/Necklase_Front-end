package com.example.necklase.Model.Interceptor;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class AuthInterceptor implements Interceptor {
    private TokenProvider tokenProvider;

    public AuthInterceptor(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        String authToken = tokenProvider.getToken();

        Request.Builder builder = original.newBuilder();
        if (authToken != null) {
            builder.header("Authorization", "Bearer " + authToken);
        }

        Request request = builder.build();
        return chain.proceed(request);
    }
}