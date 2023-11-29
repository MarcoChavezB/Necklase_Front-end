package com.example.necklase.Router;


import android.content.Context;
import android.content.Intent;

public class Router {
    public static void redirectTo(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}

