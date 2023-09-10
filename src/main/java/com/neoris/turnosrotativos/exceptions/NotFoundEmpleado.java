package com.neoris.turnosrotativos.exceptions;

public class NotFoundEmpleado extends RuntimeException{

    public NotFoundEmpleado(Long idEmpleado) {
        super("No se encontró el empleado con Id: "+idEmpleado);
    }
}
