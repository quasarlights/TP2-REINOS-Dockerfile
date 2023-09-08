package com.neoris.turnosrotativos.utils;

public class StringUtils {
     public static boolean esCampoTextoValido(String texto) {
        String regex = "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ ]+$";
        return texto.matches(regex);
    }
}
