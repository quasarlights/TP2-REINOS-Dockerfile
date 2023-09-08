package com.neoris.turnosrotativos.exceptions;

public class CampoInvalidoException extends RuntimeException {
    public CampoInvalidoException(String campo) {
        super("Solo se permiten letras en el campo '" + campo + "'.");
    }
}