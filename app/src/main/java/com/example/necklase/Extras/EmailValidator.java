package com.example.necklase.Extras;
import android.util.Patterns;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class EmailValidator {
    public static boolean isValidEmail(CharSequence target) {
        return (target != null && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
