package com.neoris.turnosrotativos.utils;

import com.neoris.turnosrotativos.exceptions.InvalidFechaNacimientoException;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class DateUtils {
//    public static int calcularEdad(LocalDate fechaNacimiento) {
//        LocalDate hoy = LocalDate.now();
//        Period periodo = Period.between(fechaNacimiento, hoy);
//        return periodo.getYears();
//    }

    public static int calcularEdad(LocalDate fechaNacimiento) {
        LocalDate hoy = LocalDate.now();
        if (fechaNacimiento.isAfter(hoy)) {
            return -1;
        }
        return Period.between(fechaNacimiento, hoy).getYears();
    }

    public static boolean calcularEdad1(LocalDate fechaNacimiento) {
        LocalDate hoy = LocalDate.now();
        if (fechaNacimiento.isAfter(hoy)) {
            throw new InvalidFechaNacimientoException();
        }
        int year = fechaNacimiento.getYear();
        int month = fechaNacimiento.getMonthValue();
        int day = fechaNacimiento.getDayOfMonth();

        return isOverEighteen( year,  month,  day);
    }
    private static boolean isOverEighteen(int year, int month, int day) {
            //NOW
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedDate = currentDate.format(formatter);
        int now = Integer.parseInt(formattedDate);
            //DOB
        int dob= year *10000 + month *100 + day *1;

            return now-dob >=180000;
    }

    /*
    function isOverEighteen(year, month, day) {

      var now = parseInt(new Date().toISOString().slice(0, 10).replace(/-/g, ''));

      var dob = year * 10000 + month * 100 + day * 1; // Coerces strings to integers

            return now - dob > 180000;

     */
}
