package com.neoris.turnosrotativos.utils;

import java.time.LocalDate;
import java.time.Period;

public class DateUtils {
    public static int calcularEdad(LocalDate fechaNacimiento) {
        LocalDate hoy = LocalDate.now();
        Period periodo = Period.between(fechaNacimiento, hoy);
        return periodo.getYears();
    }
}
