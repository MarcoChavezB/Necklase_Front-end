package com.example.necklase.Router;


import android.content.Context;
import android.content.Intent;

public class Router {
    public static void redirectTo(Context context, Class<?> destinationClass) {
        Intent intent = new Intent(context, destinationClass);
        context.startActivity(intent);
    }
}
