package com.neoris.turnosrotativos.controllers;

import com.neoris.turnosrotativos.dtos.EmpleadoDTO;
import com.neoris.turnosrotativos.dtos.EmpleadoDTOResponse;
import com.neoris.turnosrotativos.services.EmpleadoService;
import com.neoris.turnosrotativos.services.EmpleadoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/empleado")
public class EmpleadoController {
    @Autowired
    EmpleadoService empleadoService;
    private static final Logger logger = LoggerFactory.getLogger(EmpleadoController.class);

    @PostMapping
    public EmpleadoDTOResponse createEmpleado(@Valid @RequestBody EmpleadoDTO empleadoDTO){
        logger.info("LOGGEANDO en Controller FIRST empleadoDTO: "+ empleadoDTO);
        return empleadoService.createEmpleado(empleadoDTO);
    }


}
