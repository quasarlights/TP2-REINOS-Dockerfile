package com.neoris.turnosrotativos.exceptions;

public class DeleteEmpleadoException extends RuntimeException{
    public DeleteEmpleadoException(Long idEmpleado){
        super("No se encontró el empleado con Id: "+idEmpleado);
    }
}
