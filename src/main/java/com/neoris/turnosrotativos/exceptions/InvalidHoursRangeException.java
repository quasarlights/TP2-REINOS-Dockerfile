package com.neoris.turnosrotativos.exceptions;

import net.bytebuddy.implementation.bind.annotation.Super;

public class InvalidHoursRangeException extends RuntimeException{
    public InvalidHoursRangeException(Integer hsMin, Integer hsMax){
        super("El rango de horas que se puede cargar para este concepto es de " +hsMin+" - " + hsMax);
    }
}
