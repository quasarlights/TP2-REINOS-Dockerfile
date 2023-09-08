package com.neoris.turnosrotativos.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailUtils {
    public static boolean esEmailValido(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
