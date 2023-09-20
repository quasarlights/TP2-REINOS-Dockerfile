package com.neoris.turnosrotativos.configuration;

import com.neoris.turnosrotativos.dtos.EmpleadoDTO;
import com.neoris.turnosrotativos.services.EmpleadoService;
import com.neoris.turnosrotativos.services.EmpleadoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
@Component
public class DataBaseIntitializer implements CommandLineRunner {
    @Autowired
    EmpleadoService empleadoService;
    @Override
    public void run(String... args) throws Exception {
        loadEmpleados();
    }

    private void loadEmpleados(){
        List<EmpleadoDTO>empleadoDTOS= Arrays.asList(
            new EmpleadoDTO(123L, "John", "Lennon", "johnlennon@gmail.com",
                  LocalDate.parse("1940-10-09"), LocalDate.parse("1961-02-09")),
            new EmpleadoDTO(1234L, "Paul", "McCartney", "paulmaccartney@gmail.com",
                LocalDate.parse("1942-06-18"), LocalDate.parse("1961-02-09")),
        new EmpleadoDTO(12345L, "Geoge", "Harrison", "georgeharrinson@gmail.com",
                LocalDate.parse("1943-02-25"), LocalDate.parse("1961-02-09")),
        new EmpleadoDTO(123456L, "Ringo", "Starr", "ringostarr@gmail.com",
                LocalDate.parse("1940-07-07"), LocalDate.parse("1961-02-09"))
        );

        empleadoDTOS.forEach(empleadoDTO -> empleadoService.createEmpleado(empleadoDTO));
    }
}
*/

